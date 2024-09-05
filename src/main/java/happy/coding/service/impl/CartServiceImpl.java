package happy.coding.service.impl;

import happy.coding.bean.model.*;
import happy.coding.bean.vo.param.CartAddParam;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketCartMapper;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.mapper.MarketGoodsProductMapper;
import happy.coding.service.CartService;
import happy.coding.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name CartServiceImpl
 * @description Cart service implement.
 * @since 2024-09-04 16:52
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private MarketCartMapper marketCartMapper;
    @Autowired
    private MarketGoodsMapper marketGoodsMapper;
    @Autowired
    private MarketGoodsProductMapper marketGoodsProductMapper;
    @Autowired
    private GoodsService goodsService;

    @Override
    public long goodscount() {

        long count = 0;
        if (UserInfoContext.isLogined()) {
            MarketCartExample marketCartExample = new MarketCartExample();
            marketCartExample.createCriteria()
                    .andUserIdEqualTo(UserInfoContext.getUserId())
                    .andDeletedEqualTo(false);
            List<MarketCart> marketCartList = marketCartMapper.selectByExample(marketCartExample);
            count = marketCartList.stream().map(item -> item.getNumber().longValue()).reduce(Long::sum).get();
        }
        return count;
    }

    @Override
    public Map<String, Object> index() {

        List<MarketCart> cartList = listAll();

        Map<String, Object> cartTotal = new HashMap<>();
        cartTotal.put("goodsCount", calcGoodsCount(cartList));
        cartTotal.put("GoodsAmount", calcGoodsAmount(cartList));
        List<MarketCart> checkedCartList = cartList.stream().filter(item -> item.getChecked()).toList();
        cartTotal.put("checkedGoodsCount", calcGoodsCount(checkedCartList));
        cartTotal.put("checkedGoodsAmount", calcGoodsAmount(checkedCartList));

        Map<String, Object> index = new HashMap<>();
        index.put("cartList", cartList);
        index.put("cartTotal", cartTotal);
        return index;
    }

    @Override
    public long add(CartAddParam cartAddParam) {

        Integer userId = UserInfoContext.getUserId();

        MarketGoods goods = marketGoodsMapper.selectByPrimaryKey(cartAddParam.getGoodsId());
        MarketGoodsProduct product = marketGoodsProductMapper.selectByPrimaryKey(cartAddParam.getProductId());
        List<MarketGoodsSpecification> specificationList = goodsService.selectSpecificationByGoodsId(cartAddParam.getGoodsId());
        Date now = new Date();

        MarketCart marketCart = new MarketCart();
        marketCart.setUserId(userId);
        marketCart.setGoodsId(cartAddParam.getGoodsId());
        marketCart.setGoodsSn(goods.getGoodsSn());
        marketCart.setGoodsName(goods.getName());
        marketCart.setProductId(cartAddParam.getProductId());
        marketCart.setPrice(product.getPrice());
        marketCart.setNumber(cartAddParam.getNumber());
        marketCart.setSpecifications(specificationList.stream()
                .map(item -> item.getSpecification())
                .toArray(String[]::new)
        );
        marketCart.setChecked(true);
        marketCart.setPicUrl(product.getUrl());
        marketCart.setAddTime(now);
        marketCart.setUpdateTime(now);
        marketCart.setDeleted(false);
        marketCartMapper.insertSelective(marketCart);

        long goodscount = goodscount();
        return goodscount;
    }

    private List<MarketCart> listAll() {

        Integer userId = UserInfoContext.getUserId();
        MarketCartExample marketCartExample = new MarketCartExample();
        marketCartExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andDeletedEqualTo(false);
        marketCartExample.setOrderByClause("add_time DESC");
        List<MarketCart> marketCartList = marketCartMapper.selectByExample(marketCartExample);
        return marketCartList;
    }

    private int calcGoodsCount(List<MarketCart> list) {

        return list.stream()
                .map(item -> item.getNumber().intValue())
                .reduce(Integer::sum)
                .get();
    }

    private BigDecimal calcGoodsAmount(List<MarketCart> list) {

        return list.stream()
                .map(item -> item.getPrice())
                .reduce((bd0, bd1) -> bd0.add(bd1))
                .get();
    }
}
