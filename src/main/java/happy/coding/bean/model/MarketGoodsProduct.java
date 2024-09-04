package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品货品表
 */
@Schema(description="商品货品表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketGoodsProduct implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 商品表的商品ID
    */
    @Schema(description="商品表的商品ID")
    private Integer goodsId;

    /**
    * 商品规格值列表，采用JSON数组格式
    */
    @Schema(description="商品规格值列表，采用JSON数组格式")
    private String[] specifications;

    /**
    * 商品货品价格
    */
    @Schema(description="商品货品价格")
    private BigDecimal price;

    /**
    * 商品货品数量
    */
    @Schema(description="商品货品数量")
    private Integer number;

    /**
    * 商品货品图片
    */
    @Schema(description="商品货品图片")
    private String url;

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