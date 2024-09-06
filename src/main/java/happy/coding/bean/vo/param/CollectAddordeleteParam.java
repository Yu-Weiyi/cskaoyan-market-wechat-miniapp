package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name CollectAddordeleteParam
 * @description Collect add or delete param.
 * @since 2024-09-06 21:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectAddordeleteParam {

    private Byte type;
    private Integer valueId;
}
