package happy.coding.bean.vo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name OrderSubmitData
 * @description Order submit data.
 * @since 2024-09-07 11:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSubmitData {

    private Integer grouponLinkId;
    private Integer orderId;
}
