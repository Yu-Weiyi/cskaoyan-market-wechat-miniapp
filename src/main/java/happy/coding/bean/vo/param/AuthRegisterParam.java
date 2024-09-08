package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name AuthRegisterParam
 * @description Auth register param.
 * @since 2024-09-09 04:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterParam {

    private String username;
    private String password;
    private String mobile;
    private String code;
    private String wxCode;
}
