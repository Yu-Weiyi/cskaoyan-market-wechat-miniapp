package happy.coding.bean.model;

import java.util.Date;

/**
 * 团购活动表
 */
public class MarketGroupon {
    private Integer id;

    /**
    * 关联的订单ID
    */
    private Integer orderId;

    /**
    * 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
    */
    private Integer grouponId;

    /**
    * 团购规则ID，关联market_groupon_rules表ID字段
    */
    private Integer rulesId;

    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 团购分享图片地址
    */
    private String shareUrl;

    /**
    * 开团用户ID
    */
    private Integer creatorUserId;

    /**
    * 开团时间
    */
    private Date creatorUserTime;

    /**
    * 团购活动状态，开团未支付则0，开团中则1，开团失败则2
    */
    private Short status;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(Integer grouponId) {
        this.grouponId = grouponId;
    }

    public Integer getRulesId() {
        return rulesId;
    }

    public void setRulesId(Integer rulesId) {
        this.rulesId = rulesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Date getCreatorUserTime() {
        return creatorUserTime;
    }

    public void setCreatorUserTime(Date creatorUserTime) {
        this.creatorUserTime = creatorUserTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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