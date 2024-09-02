package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name AuthLoginParam
 * @description Auth login param.
 * @since 2024-09-02 19:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginParam {

    private String username;
    private String password;
}
