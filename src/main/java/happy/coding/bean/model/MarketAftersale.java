package happy.coding.bean.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 售后表
 */
public class MarketAftersale {
    private Integer id;

    /**
    * 售后编号
    */
    private String aftersaleSn;

    /**
    * 订单ID
    */
    private Integer orderId;

    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
    */
    private Short type;

    /**
    * 退款原因
    */
    private String reason;

    /**
    * 退款金额
    */
    private BigDecimal amount;

    /**
    * 退款凭证图片链接数组
    */
    private String pictures;

    /**
    * 退款说明
    */
    private String comment;

    /**
    * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
    */
    private Short status;

    /**
    * 管理员操作时间
    */
    private Date handleTime;

    /**
    * 添加时间
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

    public String getAftersaleSn() {
        return aftersaleSn;
    }

    public void setAftersaleSn(String aftersaleSn) {
        this.aftersaleSn = aftersaleSn;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
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