package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知管理员表
 */
@Schema(description="通知管理员表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketNoticeAdmin implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 通知ID
    */
    @Schema(description="通知ID")
    private Integer noticeId;

    /**
    * 通知标题
    */
    @Schema(description="通知标题")
    private String noticeTitle;

    /**
    * 接收通知的管理员ID
    */
    @Schema(description="接收通知的管理员ID")
    private Integer adminId;

    /**
    * 阅读时间，如果是NULL则是未读状态
    */
    @Schema(description="阅读时间，如果是NULL则是未读状态")
    private Date readTime;

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