package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import happy.coding.bean.model.MarketOrder;
import happy.coding.bean.model.MarketOrderExample;
import happy.coding.bean.model.MarketOrderGoods;
import happy.coding.bean.model.MarketOrderGoodsExample;
import happy.coding.bean.vo.data.HandleOptionData;
import happy.coding.bean.vo.data.OrderDetailData;
import happy.coding.bean.vo.data.OrderListData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.constant.OrderStatusConstant;
import happy.coding.context.PageInfoContext;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.mapper.MarketOrderGoodsMapper;
import happy.coding.mapper.MarketOrderMapper;
import happy.coding.service.GroupService;
import happy.coding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name OrderServiceImpl
 * @description Order interface implement.
 * @since 2024-09-04 10:10
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MarketOrderMapper marketOrderMapper;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MarketOrderGoodsMapper marketOrderGoodsMapper;

    @Override
    public long countByStatus(short status) {

        MarketOrderExample marketOrderExample = new MarketOrderExample();
        marketOrderExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andOrderStatusEqualTo(status)
                .andDeletedEqualTo(false);
        long num = marketOrderMapper.countByExample(marketOrderExample);
        return num;
    }

    @Override
    public long countUnpaid() {

        return countByStatus((short) 101);

    }

    @Override
    public long countUnShip() {

        return countByStatus((short) 201);
    }

    @Override
    public long countUnrecv() {

        return countByStatus((short) 101);
    }

    @Override
    public long countUnComment() {

        return countByStatus((short) 101);
    }

    @Override
    public List<OrderListData> list(int showType, int page, int limit) {

        int userId = UserInfoContext.getUserId();

        MarketOrderExample marketOrderExample = new MarketOrderExample();
        marketOrderExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andDeletedEqualTo(false);
        marketOrderExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketOrder> marketOrderList = marketOrderMapper.selectByExample(marketOrderExample);

        PageInfo pageInfo = new PageInfo<>(marketOrderList);
        PageInfoContext.serPageInfo(pageInfo);

        List<OrderListData> list = new ArrayList<>();
        for (MarketOrder marketOrder : marketOrderList) {
            OrderListData orderListData = new OrderListData();
            orderListData.setActualPrice(marketOrder.getActualPrice());
            orderListData.setAftersaleStatus(marketOrder.getAftersaleStatus());
            orderListData.setId(marketOrder.getId());
            orderListData.setOrderSn(marketOrder.getOrderSn());

            orderListData.setIsGroupin(groupService.isGroupIn(marketOrder.getId()));

            MarketOrderGoodsExample marketOrderGoodsExample = new MarketOrderGoodsExample();
            marketOrderGoodsExample.createCriteria()
                    .andOrderIdEqualTo(marketOrder.getId())
                    .andDeletedEqualTo(false);
            List<MarketOrderGoods> marketOrderGoodsList = marketOrderGoodsMapper.selectByExample(marketOrderGoodsExample);
            orderListData.setGoodsList(marketOrderGoodsList);

            for (OrderStatusConstant constant : OrderStatusConstant.values()) {
                if (constant.getOrderStatus() == marketOrder.getOrderStatus()) {
                    orderListData.setOrderStatusText(constant.getOrderStatusText());
                    orderListData.setHandleOption(constant.getHandleOption());
                    break;
                }
            }

            list.add(orderListData);
        }
        return list;
    }

    @Override
    public Map<String, Object> detail(Integer orderId) {

        MarketOrder marketOrder = marketOrderMapper.selectByPrimaryKey(orderId);

        List<MarketOrderGoods> orderGoods = listGoodsByOrderId(orderId);

        OrderDetailData orderInfo = new OrderDetailData();
        orderInfo.setId(orderId);
        orderInfo.setOrderSn(marketOrder.getOrderSn());
        orderInfo.setMessage(marketOrder.getMessage());
        orderInfo.setMobile(marketOrder.getMobile());
        orderInfo.setAddTime(marketOrder.getAddTime());
        orderInfo.setAddress(marketOrder.getAddress());
        orderInfo.setConsignee(marketOrder.getConsignee());
        orderInfo.setActualPrice(marketOrder.getActualPrice());
        orderInfo.setCouponPrice(marketOrder.getCouponPrice());
        orderInfo.setFreightPrice(marketOrder.getFreightPrice());
        orderInfo.setGoodsPrice(marketOrder.getGoodsPrice());
        orderInfo.setAftersaleStatus(marketOrder.getAftersaleStatus());

        for (OrderStatusConstant constant : OrderStatusConstant.values()) {
            if (constant.getOrderStatus() == marketOrder.getOrderStatus()) {
                orderInfo.setOrderStatusText(constant.getOrderStatusText());
                orderInfo.setHandleOption(constant.getHandleOption());
                break;
            }
        }

        return Map.of("orderInfo", orderInfo, "orderGoods", orderGoods);
    }

    private List<MarketOrderGoods> listGoodsByOrderId(Integer orderId) {

        MarketOrderGoodsExample marketOrderGoodsExample = new MarketOrderGoodsExample();
        marketOrderGoodsExample.createCriteria()
                .andOrderIdEqualTo(orderId)
                .andDeletedEqualTo(false);
        return marketOrderGoodsMapper.selectByExample(marketOrderGoodsExample);
    }
}
