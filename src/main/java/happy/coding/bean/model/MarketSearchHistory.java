package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索历史表
 */
@Schema(description="搜索历史表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketSearchHistory implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 用户表的用户ID
    */
    @Schema(description="用户表的用户ID")
    private Integer userId;

    /**
    * 搜索关键字
    */
    @Schema(description="搜索关键字")
    private String keyword;

    /**
    * 搜索来源，如pc、wx、app
    */
    @Schema(description="搜索来源，如pc、wx、app")
    private String from;

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