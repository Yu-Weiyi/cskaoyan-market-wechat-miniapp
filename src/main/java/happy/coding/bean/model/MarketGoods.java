package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品基本信息表
 */
@Schema(description="商品基本信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketGoods implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 商品编号
    */
    @Schema(description="商品编号")
    private String goodsSn;

    /**
    * 商品名称
    */
    @Schema(description="商品名称")
    private String name;

    /**
    * 商品所属类目ID
    */
    @Schema(description="商品所属类目ID")
    private Integer categoryId;

    @Schema(description="")
    private Integer brandId;

    /**
    * 商品宣传图片列表，采用JSON数组格式
    */
    @Schema(description="商品宣传图片列表，采用JSON数组格式")
    private String[] gallery;

    /**
    * 商品关键字，采用逗号间隔
    */
    @Schema(description="商品关键字，采用逗号间隔")
    private String keywords;

    /**
    * 商品简介
    */
    @Schema(description="商品简介")
    private String brief;

    /**
    * 是否上架
    */
    @Schema(description="是否上架")
    private Boolean isOnSale;

    @Schema(description="")
    private Short sortOrder;

    /**
    * 商品页面商品图片
    */
    @Schema(description="商品页面商品图片")
    private String picUrl;

    /**
    * 商品分享海报
    */
    @Schema(description="商品分享海报")
    private String shareUrl;

    /**
    * 是否新品首发，如果设置则可以在新品首发页面展示
    */
    @Schema(description="是否新品首发，如果设置则可以在新品首发页面展示")
    private Boolean isNew;

    /**
    * 是否人气推荐，如果设置则可以在人气推荐页面展示
    */
    @Schema(description="是否人气推荐，如果设置则可以在人气推荐页面展示")
    private Boolean isHot;

    /**
    * 商品单位，例如件、盒
    */
    @Schema(description="商品单位，例如件、盒")
    private String unit;

    /**
    * 专柜价格
    */
    @Schema(description="专柜价格")
    private BigDecimal counterPrice;

    /**
    * 零售价格
    */
    @Schema(description="零售价格")
    private BigDecimal retailPrice;

    /**
    * 商品详细介绍，是富文本格式
    */
    @Schema(description="商品详细介绍，是富文本格式")
    private String detail;

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