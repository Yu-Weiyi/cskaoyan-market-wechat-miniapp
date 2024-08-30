package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 行政区域表
 */
@Schema(description="行政区域表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketRegion implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0
    */
    @Schema(description="行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
    private Integer pid;

    /**
    * 行政区域名称
    */
    @Schema(description="行政区域名称")
    private String name;

    /**
    * 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县
    */
    @Schema(description="行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县")
    private Byte type;

    /**
    * 行政区域编码
    */
    @Schema(description="行政区域编码")
    private Integer code;

    private static final long serialVersionUID = 1L;
}