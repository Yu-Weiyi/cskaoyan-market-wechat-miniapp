package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name CartCheckedParam
 * @description Cart checked param.
 * @since 2024-09-05 13:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCheckedParam {

    private List<Integer> productIds;
    private Boolean isChecked;
}
