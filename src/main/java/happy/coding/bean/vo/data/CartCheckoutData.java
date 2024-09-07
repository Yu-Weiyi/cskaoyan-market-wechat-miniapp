package happy.coding.bean.vo.data;

import happy.coding.bean.model.MarketAddress;
import happy.coding.bean.model.MarketCart;
import happy.coding.bean.model.MarketGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name CartCheckoutData
 * @description Cart checkout data.
 * @since 2024-09-06 21:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCheckoutData {

    private Integer cartId;
    private Integer addressId;
    private Integer couponId;
    private Integer userCouponId;

    private BigDecimal grouponPrice;//无效 团购价
    private BigDecimal goodsTotalPrice;// 原价总价
    private BigDecimal couponPrice;// 优惠券减免
    private BigDecimal freightPrice;// 运费
    private BigDecimal orderTotalPrice;// 订单总价
    private BigDecimal actualPrice;// 实付（与上相同即可）
    private Integer availableCouponLength;
    private List<MarketCart> checkedGoodsList;

    private MarketAddress checkedAddress;
}
