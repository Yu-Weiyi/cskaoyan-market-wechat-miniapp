package happy.coding.bean.vo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name HandleOptionData
 * @description Handle option data.
 * @since 2024-09-05 16:53
 */
@Data
@AllArgsConstructor
public class HandleOptionData {

    // 取消订单
    private boolean cancel;
    // 去付款
    private boolean pay;
    // 申请退款
    private boolean refund;
    // 确认收货
    private boolean confirm;
    // 申请售后
    private boolean aftersale;
    // 去评价
    private boolean comment;
    // 再次购买
    private boolean rebuy;
    // 删除订单
    private boolean delete;
}
