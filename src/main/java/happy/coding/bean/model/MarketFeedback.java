package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 意见反馈表
 */
@Schema(description="意见反馈表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketFeedback implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 用户表的用户ID
    */
    @Schema(description="用户表的用户ID")
    private Integer userId;

    /**
    * 用户名称
    */
    @Schema(description="用户名称")
    private String username;

    /**
    * 手机号
    */
    @Schema(description="手机号")
    private String mobile;

    /**
    * 反馈类型
    */
    @Schema(description="反馈类型")
    private String feedType;

    /**
    * 反馈内容
    */
    @Schema(description="反馈内容")
    private String content;

    /**
    * 状态
    */
    @Schema(description="状态")
    private Integer status;

    /**
    * 是否含有图片
    */
    @Schema(description="是否含有图片")
    private Boolean hasPicture;

    /**
    * 图片地址列表，采用JSON数组格式
    */
    @Schema(description="图片地址列表，采用JSON数组格式")
    private String[] picUrls;

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