package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 团购规则表
 */
@Schema(description="团购规则表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketGrouponRules implements Serializable {
    @Schema(description="")
    private Integer id;

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
    * 商品图片或者商品货品图片
    */
    @Schema(description="商品图片或者商品货品图片")
    private String picUrl;

    /**
    * 优惠金额
    */
    @Schema(description="优惠金额")
    private BigDecimal discount;

    /**
    * 达到优惠条件的人数
    */
    @Schema(description="达到优惠条件的人数")
    private Integer discountMember;

    /**
    * 团购过期时间
    */
    @Schema(description="团购过期时间")
    private Date expireTime;

    /**
    * 团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2
    */
    @Schema(description="团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2")
    private Short status;

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