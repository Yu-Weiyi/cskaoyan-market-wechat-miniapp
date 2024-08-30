package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统配置表
 */
@Schema(description="系统配置表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketSystem implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 系统配置名
    */
    @Schema(description="系统配置名")
    private String keyName;

    /**
    * 系统配置值
    */
    @Schema(description="系统配置值")
    private String keyValue;

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