package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品规格表
 */
@Schema(description="商品规格表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketGoodsSpecification implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 商品表的商品ID
    */
    @Schema(description="商品表的商品ID")
    private Integer goodsId;

    /**
    * 商品规格名称
    */
    @Schema(description="商品规格名称")
    private String specification;

    /**
    * 商品规格值
    */
    @Schema(description="商品规格值")
    private String value;

    /**
    * 商品规格图片
    */
    @Schema(description="商品规格图片")
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