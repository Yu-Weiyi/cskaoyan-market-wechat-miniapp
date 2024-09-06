package happy.coding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author stone
 * @date 2023/04/21 20:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsSendResult {
    String jsonResult;
    String code;
}
