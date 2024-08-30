package happy.coding.bean.model;

import java.util.Date;

/**
 * 意见反馈表
 */
public class MarketFeedback {
    private Integer id;

    /**
    * 用户表的用户ID
    */
    private Integer userId;

    /**
    * 用户名称
    */
    private String username;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 反馈类型
    */
    private String feedType;

    /**
    * 反馈内容
    */
    private String content;

    /**
    * 状态
    */
    private Integer status;

    /**
    * 是否含有图片
    */
    private Boolean hasPicture;

    /**
    * 图片地址列表，采用JSON数组格式
    */
    private String picUrls;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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