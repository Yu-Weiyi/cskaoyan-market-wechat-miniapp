package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import happy.coding.bean.model.*;
import happy.coding.bean.vo.data.*;
import happy.coding.bean.vo.param.OrderSubmitParam;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.constant.OrderStatusConstant;
import happy.coding.context.PageInfoContext;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.exception.StatusException;
import happy.coding.mapper.*;
import happy.coding.service.AddressService;
import happy.coding.service.FreightService;
import happy.coding.service.GroupService;
import happy.coding.service.OrderService;
import happy.coding.util.SonwFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private MarketAddressMapper marketAddressMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MarketCartMapper marketCartMapper;
    @Autowired
    private MarketGoodsProductMapper marketGoodsProductMapper;
    @Autowired
    private MarketCouponMapper marketCouponMapper;
    @Autowired
    private MarketCouponUserMapper marketCouponUserMapper;
    @Autowired
    private FreightService freightService;

    private SonwFlakeUtil sonwFlakeUtil;

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
        MarketOrderExample.Criteria criteria = marketOrderExample.createCriteria();
        criteria
                .andUserIdEqualTo(userId)
                .andDeletedEqualTo(false);

        switch (showType) {
            case 0:
                break;
            case 1:
                criteria.andOrderStatusEqualTo(OrderStatusConstant.UNPAID.getOrderStatus());
                break;
            case 2:
                criteria.andOrderStatusEqualTo(OrderStatusConstant.PAID.getOrderStatus());
                break;
            case 3:
                criteria.andOrderStatusEqualTo(OrderStatusConstant.SHIPPED.getOrderStatus());
                break;
            case 4:
                criteria.andOrderStatusIn(List.of(
                        (short) OrderStatusConstant.USER_RECEIVED.getOrderStatus(),
                        (short) OrderStatusConstant.SYSTEM_RECEIVED.getOrderStatus()
                ));
                break;
            default:
                throw new StatusException(ErrorCodeConstant.ORDER_STATUS_ERROR);
        }

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

    @Override
    public void cancel(Integer orderId) {

        update(orderId, List.of(OrderStatusConstant.UNPAID), OrderStatusConstant.USER_CANCELLED);
    }

    @Override
    public void refund(Integer orderId) {

        update(orderId, List.of(OrderStatusConstant.PAID), OrderStatusConstant.APPLY_REFUND);
    }

    @Override
    public void confirm(Integer orderId) {

        update(orderId, List.of(OrderStatusConstant.SHIPPED), OrderStatusConstant.USER_RECEIVED);
    }

    @Override
    public void delete(Integer orderId) {

        MarketOrder marketOrder = marketOrderMapper.selectByPrimaryKey(orderId);

        if (!List.of(
                OrderStatusConstant.USER_RECEIVED.getOrderStatus(),
                OrderStatusConstant.SYSTEM_RECEIVED.getOrderStatus(),
                OrderStatusConstant.USER_CANCELLED.getOrderStatus(),
                OrderStatusConstant.SYSTEM_CANCELLED.getOrderStatus(),
                OrderStatusConstant.REFUNDED.getOrderStatus()
        ).contains(marketOrder.getOrderStatus())) {
            throw new StatusException(ErrorCodeConstant.ORDER_STATUS_ERROR);
        }

        if (!marketOrder.getUserId().equals(UserInfoContext.getUserId())) {
            throw new StatusException(ErrorCodeConstant.ORDER_STATUS_ERROR);
        }

        MarketOrder update = new MarketOrder();
        update.setId(orderId);
        update.setDeleted(true);
        update.setUpdateTime(new Date());
        marketOrderMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public OrderSubmitData submit(OrderSubmitParam orderSubmitParam) {

        Date now = new Date();
        MarketAddress marketAddress = marketAddressMapper.selectByPrimaryKey(orderSubmitParam.getAddressId());

        if (marketAddress == null) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        MarketOrder marketOrder = new MarketOrder();
        marketOrder.setUserId(UserInfoContext.getUserId());
        marketOrder.setOrderSn(sonwFlakeUtil.getUniqueId());
        marketOrder.setAftersaleStatus((short) 0);
        marketOrder.setConsignee(marketAddress.getName());
        marketOrder.setMobile(marketAddress.getTel());
        marketOrder.setAddress(addressService.getFullAddress(marketAddress));
        marketOrder.setMessage(orderSubmitParam.getMessage());

        // useless
        marketOrder.setGrouponPrice(BigDecimal.ZERO);
        marketOrder.setIntegralPrice(BigDecimal.ZERO);

        BigDecimal goodsTotalPrice = BigDecimal.ZERO;
        if (orderSubmitParam.getCartId() == 0) {// select all checked
            MarketCartExample marketCartExample = new MarketCartExample();
            marketCartExample.createCriteria()
                    .andUserIdEqualTo(UserInfoContext.getUserId())
                    .andCheckedEqualTo(true)
                    .andDeletedEqualTo(false);
            List<MarketCart> marketCartList = marketCartMapper.selectByExample(marketCartExample);
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
            MarketCart marketCart = marketCartMapper.selectByPrimaryKey(orderSubmitParam.getCartId());
            MarketGoodsProduct marketGoodsProduct = marketGoodsProductMapper.selectByPrimaryKey(marketCart.getProductId());
            goodsTotalPrice = marketGoodsProduct.getPrice();
        }
        marketOrder.setGoodsPrice(goodsTotalPrice);

        BigDecimal couponPrice = BigDecimal.ZERO;
        if (orderSubmitParam.getCouponId() != null && orderSubmitParam.getCouponId() > 0) {
            MarketCoupon marketCoupon = marketCouponMapper.selectByPrimaryKey(orderSubmitParam.getCouponId());
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

            MarketCouponUser marketCouponUser = marketCouponUserMapper.selectByPrimaryKey(orderSubmitParam.getUserCouponId());
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
        marketOrder.setCouponPrice(couponPrice);

        BigDecimal freightPrice = freightService.calc(goodsTotalPrice);
        marketOrder.setFreightPrice(freightPrice);

        BigDecimal orderTotalPrice = goodsTotalPrice.add(couponPrice.negate()).add(freightPrice);
        marketOrder.setOrderPrice(orderTotalPrice);

        BigDecimal integralPrice = BigDecimal.ZERO.negate();
        marketOrder.setActualPrice(orderTotalPrice.add(integralPrice));

        marketOrder.setOrderStatus((short) 101);
        marketOrder.setAddTime(now);
        marketOrder.setUpdateTime(now);
        marketOrder.setDeleted(false);

        marketOrderMapper.insertSelective(marketOrder);

        OrderSubmitData orderSubmitData = new OrderSubmitData(0, marketOrder.getId());
        return orderSubmitData;
    }

    private void update(Integer orderId, List<OrderStatusConstant> conditionalStatusList, OrderStatusConstant newStatus) {

        MarketOrder marketOrder = marketOrderMapper.selectByPrimaryKey(orderId);

        if (!conditionalStatusList.stream().map(item -> item.getOrderStatus()).toList().contains(marketOrder.getOrderStatus())) {
            throw new StatusException(ErrorCodeConstant.ORDER_STATUS_ERROR);
        }

        if (!marketOrder.getUserId().equals(UserInfoContext.getUserId())) {
            throw new StatusException(ErrorCodeConstant.ORDER_STATUS_ERROR);
        }

        MarketOrder update = new MarketOrder();
        update.setId(orderId);
        update.setOrderStatus(newStatus.getOrderStatus());
        update.setUpdateTime(new Date());
        marketOrderMapper.updateByPrimaryKeySelective(update);
    }

    private List<MarketOrderGoods> listGoodsByOrderId(Integer orderId) {

        MarketOrderGoodsExample marketOrderGoodsExample = new MarketOrderGoodsExample();
        marketOrderGoodsExample.createCriteria()
                .andOrderIdEqualTo(orderId)
                .andDeletedEqualTo(false);
        return marketOrderGoodsMapper.selectByExample(marketOrderGoodsExample);
    }
}
