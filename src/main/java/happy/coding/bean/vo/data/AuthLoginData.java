package happy.coding.bean.vo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name AuthLoginData
 * @description Auth login data.
 * @since 2024-09-02 19:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginData {

    private String token;
    private Map userInfo;
}
