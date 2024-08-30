package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车商品表
 */
@Schema(description="购物车商品表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketCart implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 用户表的用户ID
    */
    @Schema(description="用户表的用户ID")
    private Integer userId;

    /**
    * 商品表的商品ID
    */
    @Schema(description="商品表的商品ID")
    private Integer goodsId;

    /**
    * 商品编号
    */
    @Schema(description="商品编号")
    private String goodsSn;

    /**
    * 商品名称
    */
    @Schema(description="商品名称")
    private String goodsName;

    /**
    * 商品货品表的货品ID
    */
    @Schema(description="商品货品表的货品ID")
    private Integer productId;

    /**
    * 商品货品的价格
    */
    @Schema(description="商品货品的价格")
    private BigDecimal price;

    /**
    * 商品货品的数量
    */
    @Schema(description="商品货品的数量")
    private Short number;

    /**
    * 商品规格值列表，采用JSON数组格式
    */
    @Schema(description="商品规格值列表，采用JSON数组格式")
    private String specifications;

    /**
    * 购物车中商品是否选择状态
    */
    @Schema(description="购物车中商品是否选择状态")
    private Boolean checked;

    /**
    * 商品图片或者商品货品图片
    */
    @Schema(description="商品图片或者商品货品图片")
    private String picUrl;

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