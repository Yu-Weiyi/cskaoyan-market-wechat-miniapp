package happy.coding.bean.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 团购规则表
 */
public class MarketGrouponRules {
    private Integer id;

    /**
    * 商品表的商品ID
    */
    private Integer goodsId;

    /**
    * 商品名称
    */
    private String goodsName;

    /**
    * 商品图片或者商品货品图片
    */
    private String picUrl;

    /**
    * 优惠金额
    */
    private BigDecimal discount;

    /**
    * 达到优惠条件的人数
    */
    private Integer discountMember;

    /**
    * 团购过期时间
    */
    private Date expireTime;

    /**
    * 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getDiscountMember() {
        return discountMember;
    }

    public void setDiscountMember(Integer discountMember) {
        this.discountMember = discountMember;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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