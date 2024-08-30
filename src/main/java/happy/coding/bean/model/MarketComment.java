package happy.coding.bean.model;

import java.util.Date;

/**
 * 评论表
 */
public class MarketComment {
    private Integer id;

    /**
    * 如果type=0，则是商品评论；如果是type=1，则是专题评论。
    */
    private Integer valueId;

    /**
    * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
    */
    private Byte type;

    /**
    * 评论内容
    */
    private String content;

    /**
    * 管理员回复内容
    */
    private String adminContent;

    /**
    * 用户表的用户ID
    */
    private Integer userId;

    /**
    * 是否含有图片
    */
    private Boolean hasPicture;

    /**
    * 图片地址列表，采用JSON数组格式
    */
    private String picUrls;

    /**
    * 评分， 1-5
    */
    private Short star;

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

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdminContent() {
        return adminContent;
    }

    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public Short getStar() {
        return star;
    }

    public void setStar(Short star) {
        this.star = star;
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