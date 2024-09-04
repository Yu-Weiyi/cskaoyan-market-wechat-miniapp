package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 专题表
 */
@Schema(description="专题表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketTopic implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 专题标题
    */
    @Schema(description="专题标题")
    private String title;

    /**
    * 专题子标题
    */
    @Schema(description="专题子标题")
    private String subtitle;

    /**
    * 专题内容，富文本格式
    */
    @Schema(description="专题内容，富文本格式")
    private String content;

    /**
    * 专题相关商品最低价
    */
    @Schema(description="专题相关商品最低价")
    private BigDecimal price;

    /**
    * 专题阅读量
    */
    @Schema(description="专题阅读量")
    private String readCount;

    /**
    * 专题图片
    */
    @Schema(description="专题图片")
    private String picUrl;

    /**
    * 排序
    */
    @Schema(description="排序")
    private Integer sortOrder;

    /**
    * 专题相关商品，采用JSON数组格式
    */
    @Schema(description="专题相关商品，采用JSON数组格式")
    private String[] goods;

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