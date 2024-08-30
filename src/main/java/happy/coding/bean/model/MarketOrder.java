package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单表
 */
@Schema(description="订单表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketOrder implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 用户表的用户ID
    */
    @Schema(description="用户表的用户ID")
    private Integer userId;

    /**
    * 订单编号
    */
    @Schema(description="订单编号")
    private String orderSn;

    /**
    * 订单状态
    */
    @Schema(description="订单状态")
    private Short orderStatus;

    /**
    * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
    */
    @Schema(description="售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
    private Short aftersaleStatus;

    /**
    * 收货人名称
    */
    @Schema(description="收货人名称")
    private String consignee;

    /**
    * 收货人手机号
    */
    @Schema(description="收货人手机号")
    private String mobile;

    /**
    * 收货具体地址
    */
    @Schema(description="收货具体地址")
    private String address;

    /**
    * 用户订单留言
    */
    @Schema(description="用户订单留言")
    private String message;

    /**
    * 商品总费用
    */
    @Schema(description="商品总费用")
    private BigDecimal goodsPrice;

    /**
    * 配送费用
    */
    @Schema(description="配送费用")
    private BigDecimal freightPrice;

    /**
    * 优惠券减免
    */
    @Schema(description="优惠券减免")
    private BigDecimal couponPrice;

    /**
    * 用户积分减免
    */
    @Schema(description="用户积分减免")
    private BigDecimal integralPrice;

    /**
    * 团购优惠价减免
    */
    @Schema(description="团购优惠价减免")
    private BigDecimal grouponPrice;

    /**
    * 订单费用， = goods_price + freight_price - coupon_price
    */
    @Schema(description="订单费用， = goods_price + freight_price - coupon_price")
    private BigDecimal orderPrice;

    /**
    * 实付费用， = order_price - integral_price
    */
    @Schema(description="实付费用， = order_price - integral_price")
    private BigDecimal actualPrice;

    /**
    * 微信付款编号
    */
    @Schema(description="微信付款编号")
    private String payId;

    /**
    * 微信付款时间
    */
    @Schema(description="微信付款时间")
    private Date payTime;

    /**
    * 发货编号
    */
    @Schema(description="发货编号")
    private String shipSn;

    /**
    * 发货快递公司
    */
    @Schema(description="发货快递公司")
    private String shipChannel;

    /**
    * 发货开始时间
    */
    @Schema(description="发货开始时间")
    private Date shipTime;

    /**
    * 实际退款金额，（有可能退款金额小于实际支付金额）
    */
    @Schema(description="实际退款金额，（有可能退款金额小于实际支付金额）")
    private BigDecimal refundAmount;

    /**
    * 退款方式
    */
    @Schema(description="退款方式")
    private String refundType;

    /**
    * 退款备注
    */
    @Schema(description="退款备注")
    private String refundContent;

    /**
    * 退款时间
    */
    @Schema(description="退款时间")
    private Date refundTime;

    /**
    * 用户确认收货时间
    */
    @Schema(description="用户确认收货时间")
    private Date confirmTime;

    /**
    * 待评价订单商品数量
    */
    @Schema(description="待评价订单商品数量")
    private Short comments;

    /**
    * 订单关闭时间
    */
    @Schema(description="订单关闭时间")
    private Date endTime;

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