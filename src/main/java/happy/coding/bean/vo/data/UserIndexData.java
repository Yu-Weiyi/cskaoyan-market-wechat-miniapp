package happy.coding.bean.vo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name UserIndexData
 * @description User index data.
 * @since 2024-09-04 09:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIndexData {

    private long unpaid;
    private long unship;
    private long unrecv;
    private long uncomment;
}