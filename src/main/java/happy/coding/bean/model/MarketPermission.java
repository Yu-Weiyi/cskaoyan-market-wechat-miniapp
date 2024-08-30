package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限表
 */
@Schema(description="权限表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketPermission implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 角色ID
    */
    @Schema(description="角色ID")
    private Integer roleId;

    /**
    * 权限
    */
    @Schema(description="权限")
    private String permission;

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