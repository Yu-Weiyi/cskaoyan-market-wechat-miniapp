package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 常见问题表
 */
@Schema(description="常见问题表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketIssue implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 问题标题
    */
    @Schema(description="问题标题")
    private String question;

    /**
    * 问题答案
    */
    @Schema(description="问题答案")
    private String answer;

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