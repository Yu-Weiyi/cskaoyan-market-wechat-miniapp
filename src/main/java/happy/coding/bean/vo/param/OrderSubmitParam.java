package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name OrderSubmitParam
 * @description Order submit param.
 * @since 2024-09-07 11:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSubmitParam {

    private Integer addressId;
    private Integer couponId;
    private Integer grouponLinkId;
    private Integer userCouponId;
    private Integer cartId;
    private String message;
}
