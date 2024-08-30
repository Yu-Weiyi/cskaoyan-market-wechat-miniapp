package happy.coding.bean.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车商品表
 */
public class MarketCart {
    private Integer id;

    /**
    * 用户表的用户ID
    */
    private Integer userId;

    /**
    * 商品表的商品ID
    */
    private Integer goodsId;

    /**
    * 商品编号
    */
    private String goodsSn;

    /**
    * 商品名称
    */
    private String goodsName;

    /**
    * 商品货品表的货品ID
    */
    private Integer productId;

    /**
    * 商品货品的价格
    */
    private BigDecimal price;

    /**
    * 商品货品的数量
    */
    private Short number;

    /**
    * 商品规格值列表，采用JSON数组格式
    */
    private String specifications;

    /**
    * 购物车中商品是否选择状态
    */
    private Boolean checked;

    /**
    * 商品图片或者商品货品图片
    */
    private String picUrl;

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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