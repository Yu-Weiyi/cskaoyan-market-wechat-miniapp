package happy.coding.constant;

import happy.coding.bean.vo.data.HandleOptionData;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.constant
 * @name OrderStatusConstant
 * @description Order status constant.
 * @since 2024-09-05 17:19
 */
@Getter
@AllArgsConstructor
public enum OrderStatusConstant {

    UNPAID((short) 101, "未付款", new HandleOptionData(true, true, false, false, false, false, false, false)),
    USER_CANCELLED((short) 102, "用户取消", new HandleOptionData(false, false, false, false, false, false, true, true)),
    SYSTEM_CANCELLED((short) 103, "系统取消", new HandleOptionData(false, false, false, false, false, false, true, true)),
    PAID((short) 201, "已付款", new HandleOptionData(false, false, true, false, false, false, true, false)),
    APPLY_REFUND((short) 202, "申请退款", new HandleOptionData(false, false, false, true, false, false, false, false)),
    REFUNDED((short) 203, "已退款", new HandleOptionData(false, false, false, false, false, false, true, true)),
    SHIPPED((short) 301, "已发货", new HandleOptionData(false, false, false, true, false, false, true, false)),
    USER_RECEIVED((short) 401, "用户收货", new HandleOptionData(false, false, false, false, true, true, true, true)),
    SYSTEM_RECEIVED((short) 402, "系统收货", new HandleOptionData(false, false, false, false, true, true, true, true));

    private final short orderStatus;
    private final String orderStatusText;
    private final HandleOptionData handleOption;
}
