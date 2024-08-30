package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员表
 */
@Schema(description="管理员表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketAdmin implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 管理员名称
    */
    @Schema(description="管理员名称")
    private String username;

    /**
    * 管理员密码
    */
    @Schema(description="管理员密码")
    private String password;

    /**
    * 最近一次登录IP地址
    */
    @Schema(description="最近一次登录IP地址")
    private String lastLoginIp;

    /**
    * 最近一次登录时间
    */
    @Schema(description="最近一次登录时间")
    private Date lastLoginTime;

    /**
    * 头像图片
    */
    @Schema(description="头像图片")
    private String avatar;

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

    /**
    * 角色列表
    */
    @Schema(description="角色列表")
    private String roleIds;

    private static final long serialVersionUID = 1L;
}