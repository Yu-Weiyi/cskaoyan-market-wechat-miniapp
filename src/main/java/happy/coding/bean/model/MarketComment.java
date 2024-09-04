package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论表
 */
@Schema(description="评论表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketComment implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 如果type=0，则是商品评论；如果是type=1，则是专题评论。
    */
    @Schema(description="如果type=0，则是商品评论；如果是type=1，则是专题评论。")
    private Integer valueId;

    /**
    * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
    */
    @Schema(description="评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；")
    private Byte type;

    /**
    * 评论内容
    */
    @Schema(description="评论内容")
    private String content;

    /**
    * 管理员回复内容
    */
    @Schema(description="管理员回复内容")
    private String adminContent;

    /**
    * 用户表的用户ID
    */
    @Schema(description="用户表的用户ID")
    private Integer userId;

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
    * 评分， 1-5
    */
    @Schema(description="评分， 1-5")
    private Short star;

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