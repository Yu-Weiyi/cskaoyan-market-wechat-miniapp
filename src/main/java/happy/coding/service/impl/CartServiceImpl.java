package happy.coding.service.impl;

import happy.coding.bean.model.MarketCart;
import happy.coding.bean.model.MarketCartExample;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketCartMapper;
import happy.coding.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

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

    @Override
    public long goodscount() {

        long count = 0;
        if (UserInfoContext.isLogined()) {
            MarketCartExample marketCartExample = new MarketCartExample();
            marketCartExample.createCriteria()
                    .andUserIdEqualTo(UserInfoContext.getUserId())
                    .andDeletedEqualTo(false);
            count = marketCartMapper.countByExample(marketCartExample);
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
