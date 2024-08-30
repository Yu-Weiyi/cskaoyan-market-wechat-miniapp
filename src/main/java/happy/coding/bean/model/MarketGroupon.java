package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 团购活动表
 */
@Schema(description="团购活动表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketGroupon implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 关联的订单ID
    */
    @Schema(description="关联的订单ID")
    private Integer orderId;

    /**
    * 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
    */
    @Schema(description="如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID")
    private Integer grouponId;

    /**
    * 团购规则ID，关联market_groupon_rules表ID字段
    */
    @Schema(description="团购规则ID，关联market_groupon_rules表ID字段")
    private Integer rulesId;

    /**
    * 用户ID
    */
    @Schema(description="用户ID")
    private Integer userId;

    /**
    * 团购分享图片地址
    */
    @Schema(description="团购分享图片地址")
    private String shareUrl;

    /**
    * 开团用户ID
    */
    @Schema(description="开团用户ID")
    private Integer creatorUserId;

    /**
    * 开团时间
    */
    @Schema(description="开团时间")
    private Date creatorUserTime;

    /**
    * 团购活动状态，开团未支付则0，开团中则1，开团失败则2
    */
    @Schema(description="团购活动状态，开团未支付则0，开团中则1，开团失败则2")
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