package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Schema(description="用户表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketUser implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 用户名称
    */
    @Schema(description="用户名称")
    private String username;

    /**
    * 用户密码
    */
    @Schema(description="用户密码")
    private String password;

    /**
    * 性别：0 未知， 1男， 1 女
    */
    @Schema(description="性别：0 未知， 1男， 1 女")
    private Byte gender;

    /**
    * 生日
    */
    @Schema(description="生日")
    private Date birthday;

    /**
    * 最近一次登录时间
    */
    @Schema(description="最近一次登录时间")
    private Date lastLoginTime;

    /**
    * 最近一次登录IP地址
    */
    @Schema(description="最近一次登录IP地址")
    private String lastLoginIp;

    /**
    * 0 普通用户，1 VIP用户，2 高级VIP用户
    */
    @Schema(description="0 普通用户，1 VIP用户，2 高级VIP用户")
    private Byte userLevel;

    /**
    * 用户昵称或网络名称
    */
    @Schema(description="用户昵称或网络名称")
    private String nickname;

    /**
    * 用户手机号码
    */
    @Schema(description="用户手机号码")
    private String mobile;

    /**
    * 用户头像图片
    */
    @Schema(description="用户头像图片")
    private String avatar;

    /**
    * 微信登录openid
    */
    @Schema(description="微信登录openid")
    private String weixinOpenid;

    /**
    * 微信登录会话KEY
    */
    @Schema(description="微信登录会话KEY")
    private String sessionKey;

    /**
    * 0 可用, 1 禁用, 2 注销
    */
    @Schema(description="0 可用, 1 禁用, 2 注销")
    private Byte status;

    /**
    * 创建时间
    */
    @Schema(description="创建时间")
    private Date addTime;

    /**
    * 更新时间
    */
    @Schema(description="更新时间")
    private Date updateTime;

    /**
    * 逻辑删除
    */
    @Schema(description="逻辑删除")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;
}