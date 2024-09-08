package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单商品表
 */
@Schema(description="订单商品表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketOrderGoods implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 订单表的订单ID
    */
    @Schema(description="订单表的订单ID")
    private Integer orderId;

    /**
    * 商品表的商品ID
    */
    @Schema(description="商品表的商品ID")
    private Integer goodsId;

    /**
    * 商品名称
    */
    @Schema(description="商品名称")
    private String goodsName;

    /**
    * 商品编号
    */
    @Schema(description="商品编号")
    private String goodsSn;

    /**
    * 商品货品表的货品ID
    */
    @Schema(description="商品货品表的货品ID")
    private Integer productId;

    /**
    * 商品货品的购买数量
    */
    @Schema(description="商品货品的购买数量")
    private Short number;

    /**
    * 商品货品的售价
    */
    @Schema(description="商品货品的售价")
    private BigDecimal price;

    /**
    * 商品货品的规格列表
    */
    @Schema(description="商品货品的规格列表")
    private String[] specifications;

    /**
    * 商品货品图片或者商品图片
    */
    @Schema(description="商品货品图片或者商品图片")
    private String picUrl;

    /**
    * 订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。
    */
    @Schema(description="订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。")
    private Integer comment;

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