package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作日志表
 */
@Schema(description="操作日志表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketLog implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 管理员
    */
    @Schema(description="管理员")
    private String admin;

    /**
    * 管理员地址
    */
    @Schema(description="管理员地址")
    private String ip;

    /**
    * 操作分类
    */
    @Schema(description="操作分类")
    private Integer type;

    /**
    * 操作动作
    */
    @Schema(description="操作动作")
    private String action;

    /**
    * 操作状态
    */
    @Schema(description="操作状态")
    private Boolean status;

    /**
    * 操作结果，或者成功消息，或者失败消息
    */
    @Schema(description="操作结果，或者成功消息，或者失败消息")
    private String result;

    /**
    * 补充信息
    */
    @Schema(description="补充信息")
    private String comment;

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