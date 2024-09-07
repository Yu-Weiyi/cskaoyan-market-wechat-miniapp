package happy.coding.service.impl;

import happy.coding.bean.model.*;
import happy.coding.bean.vo.data.CartCheckoutData;
import happy.coding.bean.vo.param.CartAddParam;
import happy.coding.bean.vo.param.CartCheckedParam;
import happy.coding.bean.vo.param.CartFastaddParam;
import happy.coding.bean.vo.param.CartUpdateParam;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.mapper.*;
import happy.coding.service.CartService;
import happy.coding.service.CouponService;
import happy.coding.service.FreightService;
import happy.coding.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    private MarketAddressMapper marketAddressMapper;
    @Autowired
    private FreightService freightService;
    @Autowired
    private MarketCouponMapper marketCouponMapper;
    @Autowired
    private MarketCouponUserMapper marketCouponUserMapper;
    @Autowired
    private CouponService couponService;

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

    @Override
    public Map<String, Object> checked(CartCheckedParam cartCheckedParam) {

        MarketCartExample marketCartExample = new MarketCartExample();
        marketCartExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andProductIdIn(cartCheckedParam.getProductIds())
                .andDeletedEqualTo(false);
        MarketCart marketCart = new MarketCart();
//        switch (cartCheckedParam.getChecked()) {
//            case 0:
//                marketCart.setChecked(false);
//                break;
//            case 1:
//                marketCart.setChecked(true);
//        }
        marketCart.setChecked(cartCheckedParam.getIsChecked());
        marketCart.setUpdateTime(new Date());
        marketCartMapper.updateByExampleSelective(marketCart, marketCartExample);

        return index();
    }

    @Override
    public void update(CartUpdateParam cartUpdateParam) {

        MarketCartExample marketCartExample = new MarketCartExample();
        marketCartExample.createCriteria()
                .andIdEqualTo(cartUpdateParam.getId())
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andGoodsIdEqualTo(cartUpdateParam.getGoodsId())
                .andProductIdEqualTo(cartUpdateParam.getProductId())
                .andDeletedEqualTo(false);
        MarketCart marketCart = new MarketCart();
        marketCart.setNumber(cartUpdateParam.getNumber());
        marketCart.setUpdateTime(new Date());
        marketCartMapper.updateByExampleSelective(marketCart, marketCartExample);
    }

    @Override
    public Map<String, Object> delete(List<Integer> productIds) {

        MarketCartExample marketCartExample = new MarketCartExample();
        marketCartExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andProductIdIn(productIds)
                .andDeletedEqualTo(false);
        MarketCart marketCart = new MarketCart();
        marketCart.setDeleted(true);
        marketCart.setUpdateTime(new Date());
        marketCartMapper.updateByExampleSelective(marketCart, marketCartExample);

        return index();
    }

    @Override
    public int fastadd(CartFastaddParam cartFastaddParam) {

        Date now = new Date();
        MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(cartFastaddParam.getGoodsId());
        MarketGoodsProduct marketGoodsProduct = marketGoodsProductMapper.selectByPrimaryKey(cartFastaddParam.getProductId());

        MarketCart marketCart = new MarketCart();
        marketCart.setUserId(UserInfoContext.getUserId());
        marketCart.setGoodsId(cartFastaddParam.getGoodsId());
        marketCart.setGoodsSn(marketGoods.getGoodsSn());
        marketCart.setGoodsName(marketGoods.getName());
        marketCart.setProductId(cartFastaddParam.getProductId());
        marketCart.setPrice(marketGoodsProduct.getPrice());
        marketCart.setNumber(cartFastaddParam.getNumber());
        marketCart.setSpecifications(marketGoodsProduct.getSpecifications());
        marketCart.setChecked(true);
        marketCart.setPicUrl(marketGoodsProduct.getUrl());
        marketCart.setAddTime(now);
        marketCart.setUpdateTime(now);
        marketCart.setDeleted(false);
        marketCartMapper.insertSelective(marketCart);
        return marketCart.getId();
    }

    @Override
    public CartCheckoutData checkout(Integer cartId, Integer addressId, Integer couponId, Integer userCouponId) {

        Date now = new Date();
        CartCheckoutData cartCheckoutData = new CartCheckoutData();
        cartCheckoutData.setCartId(cartId);
        cartCheckoutData.setAddressId(addressId);
        cartCheckoutData.setCouponId(couponId);
        cartCheckoutData.setUserCouponId(userCouponId);
        cartCheckoutData.setCheckedAddress(marketAddressMapper.selectByPrimaryKey(addressId));

        cartCheckoutData.setGrouponPrice(BigDecimal.ZERO);// useless

        BigDecimal goodsTotalPrice = BigDecimal.ZERO;
        if (cartId == 0) {// select all checked
            MarketCartExample marketCartExample = new MarketCartExample();
            marketCartExample.createCriteria()
                    .andUserIdEqualTo(UserInfoContext.getUserId())
                    .andCheckedEqualTo(true)
                    .andDeletedEqualTo(false);
            List<MarketCart> marketCartList = marketCartMapper.selectByExample(marketCartExample);
            cartCheckoutData.setCheckedGoodsList(marketCartList);
            List<MarketGoodsProduct> marketGoodsProductList = marketCartList.stream()
                    .map(MarketCart::getProductId)
                    .map(item -> marketGoodsProductMapper.selectByPrimaryKey(item))
                    .filter(item -> item != null)
                    .toList();

            goodsTotalPrice = marketGoodsProductList.stream()
                    .map(MarketGoodsProduct::getPrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
        } else {// fastadd select one goods
            MarketCart marketCart = marketCartMapper.selectByPrimaryKey(cartId);
            cartCheckoutData.setCheckedGoodsList(List.of(marketCart));
            MarketGoodsProduct marketGoodsProduct = marketGoodsProductMapper.selectByPrimaryKey(marketCart.getProductId());
            goodsTotalPrice = marketGoodsProduct.getPrice();
        }
        cartCheckoutData.setGoodsTotalPrice(goodsTotalPrice);

        BigDecimal couponPrice = BigDecimal.ZERO;
        if (couponId != null && couponId > 0) {
            MarketCoupon marketCoupon = marketCouponMapper.selectByPrimaryKey(couponId);
            if (goodsTotalPrice.compareTo(marketCoupon.getMin()) < 0) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }
            if (marketCoupon.getStatus() != 0) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }
            // too complex
//            switch (marketCoupon.getGoodsType()) {
//            }
            switch (marketCoupon.getTimeType()) {
                case 0:
                    // too complex
                    break;
                case 1:
                    if (now.before(marketCoupon.getStartTime()) || now.after(marketCoupon.getEndTime())) {
                        throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
                    }
                    break;
                default:
                    throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
            }
            if (marketCoupon.getDeleted()) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }

            MarketCouponUser marketCouponUser = marketCouponUserMapper.selectByPrimaryKey(userCouponId);
            if (!UserInfoContext.getUserId().equals(marketCouponUser.getUserId())) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }
            if (!marketCouponUser.getCouponId().equals(marketCoupon.getId())) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }
            if (marketCouponUser.getStatus() != 0 || marketCouponUser.getUsedTime() != null) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }
            if (now.before(marketCouponUser.getStartTime()) || now.after(marketCouponUser.getEndTime())) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }
            if (marketCouponUser.getDeleted()) {
                throw new QueryException(ErrorCodeConstant.INVALID_PARAM);
            }

            // coupon check pass
            couponPrice = marketCoupon.getDiscount();
        }
        cartCheckoutData.setCouponPrice(couponPrice);

        BigDecimal freightPrice = freightService.calc(goodsTotalPrice);
        cartCheckoutData.setFreightPrice(freightPrice);

        BigDecimal orderTotalPrice = goodsTotalPrice.add(couponPrice.negate()).add(freightPrice);
        cartCheckoutData.setOrderTotalPrice(orderTotalPrice);

        BigDecimal integralPrice = BigDecimal.ZERO.negate();
        cartCheckoutData.setActualPrice(orderTotalPrice.add(integralPrice));

        cartCheckoutData.setAvailableCouponLength(couponService.listUserAvailable(1, 0).size());

        return cartCheckoutData;
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
