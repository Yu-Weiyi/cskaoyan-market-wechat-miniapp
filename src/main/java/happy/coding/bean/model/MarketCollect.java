package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收藏表
 */
@Schema(description="收藏表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketCollect implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 用户表的用户ID
    */
    @Schema(description="用户表的用户ID")
    private Integer userId;

    /**
    * 如果type=0，则是商品ID；如果type=1，则是专题ID
    */
    @Schema(description="如果type=0，则是商品ID；如果type=1，则是专题ID")
    private Integer valueId;

    /**
    * 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
    */
    @Schema(description="收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID")
    private Byte type;

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