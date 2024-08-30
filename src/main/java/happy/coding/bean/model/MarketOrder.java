package happy.coding.bean.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 */
public class MarketOrder {
    private Integer id;

    /**
    * 用户表的用户ID
    */
    private Integer userId;

    /**
    * 订单编号
    */
    private String orderSn;

    /**
    * 订单状态
    */
    private Short orderStatus;

    /**
    * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
    */
    private Short aftersaleStatus;

    /**
    * 收货人名称
    */
    private String consignee;

    /**
    * 收货人手机号
    */
    private String mobile;

    /**
    * 收货具体地址
    */
    private String address;

    /**
    * 用户订单留言
    */
    private String message;

    /**
    * 商品总费用
    */
    private BigDecimal goodsPrice;

    /**
    * 配送费用
    */
    private BigDecimal freightPrice;

    /**
    * 优惠券减免
    */
    private BigDecimal couponPrice;

    /**
    * 用户积分减免
    */
    private BigDecimal integralPrice;

    /**
    * 团购优惠价减免
    */
    private BigDecimal grouponPrice;

    /**
    * 订单费用， = goods_price + freight_price - coupon_price
    */
    private BigDecimal orderPrice;

    /**
    * 实付费用， = order_price - integral_price
    */
    private BigDecimal actualPrice;

    /**
    * 微信付款编号
    */
    private String payId;

    /**
    * 微信付款时间
    */
    private Date payTime;

    /**
    * 发货编号
    */
    private String shipSn;

    /**
    * 发货快递公司
    */
    private String shipChannel;

    /**
    * 发货开始时间
    */
    private Date shipTime;

    /**
    * 实际退款金额，（有可能退款金额小于实际支付金额）
    */
    private BigDecimal refundAmount;

    /**
    * 退款方式
    */
    private String refundType;

    /**
    * 退款备注
    */
    private String refundContent;

    /**
    * 退款时间
    */
    private Date refundTime;

    /**
    * 用户确认收货时间
    */
    private Date confirmTime;

    /**
    * 待评价订单商品数量
    */
    private Short comments;

    /**
    * 订单关闭时间
    */
    private Date endTime;

    /**
    * 创建时间
    */
    private Date addTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 逻辑删除
    */
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Short getAftersaleStatus() {
        return aftersaleStatus;
    }

    public void setAftersaleStatus(Short aftersaleStatus) {
        this.aftersaleStatus = aftersaleStatus;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(BigDecimal integralPrice) {
        this.integralPrice = integralPrice;
    }

    public BigDecimal getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(BigDecimal grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getShipSn() {
        return shipSn;
    }

    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }

    public String getShipChannel() {
        return shipChannel;
    }

    public void setShipChannel(String shipChannel) {
        this.shipChannel = shipChannel;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getRefundContent() {
        return refundContent;
    }

    public void setRefundContent(String refundContent) {
        this.refundContent = refundContent;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Short getComments() {
        return comments;
    }

    public void setComments(Short comments) {
        this.comments = comments;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}