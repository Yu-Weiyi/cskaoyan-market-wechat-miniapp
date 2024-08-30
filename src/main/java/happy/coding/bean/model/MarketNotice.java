package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知表
 */
@Schema(description="通知表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketNotice implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 通知标题
    */
    @Schema(description="通知标题")
    private String title;

    /**
    * 通知内容
    */
    @Schema(description="通知内容")
    private String content;

    /**
    * 创建通知的管理员ID，如果是系统内置通知则是0.
    */
    @Schema(description="创建通知的管理员ID，如果是系统内置通知则是0.")
    private Integer adminId;

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