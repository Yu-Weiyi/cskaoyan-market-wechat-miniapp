package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色表
 */
@Schema(description="角色表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketRole implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 角色名称
    */
    @Schema(description="角色名称")
    private String name;

    /**
    * 角色描述
    */
    @Schema(description="角色描述")
    private String desc;

    /**
    * 是否启用
    */
    @Schema(description="是否启用")
    private Boolean enabled;

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