package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券用户使用表
 */
@Schema(description="优惠券用户使用表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketCouponUser implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 用户ID
    */
    @Schema(description="用户ID")
    private Integer userId;

    /**
    * 优惠券ID
    */
    @Schema(description="优惠券ID")
    private Integer couponId;

    /**
    * 使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；
    */
    @Schema(description="使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；")
    private Short status;

    /**
    * 使用时间
    */
    @Schema(description="使用时间")
    private Date usedTime;

    /**
    * 有效期开始时间
    */
    @Schema(description="有效期开始时间")
    private Date startTime;

    /**
    * 有效期截至时间
    */
    @Schema(description="有效期截至时间")
    private Date endTime;

    /**
    * 订单ID
    */
    @Schema(description="订单ID")
    private Integer orderId;

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