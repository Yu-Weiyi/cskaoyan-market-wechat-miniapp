package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品牌商表
 */
@Schema(description="品牌商表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketBrand implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 品牌商名称
    */
    @Schema(description="品牌商名称")
    private String name;

    /**
    * 品牌商简介
    */
    @Schema(description="品牌商简介")
    private String desc;

    /**
    * 品牌商页的品牌商图片
    */
    @Schema(description="品牌商页的品牌商图片")
    private String picUrl;

    @Schema(description="")
    private Byte sortOrder;

    /**
    * 品牌商的商品低价，仅用于页面展示
    */
    @Schema(description="品牌商的商品低价，仅用于页面展示")
    private BigDecimal floorPrice;

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