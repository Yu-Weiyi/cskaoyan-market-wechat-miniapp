package happy.coding.service;

import happy.coding.bean.vo.data.AuthLoginData;
import happy.coding.bean.vo.param.AuthRegisterParam;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name AuthService
 * @description 认证鉴权服务接口。
 * @since 2024-09-02 19:11
 */
public interface AuthService {

    void regCaptcha(String mobile);

    AuthLoginData register(AuthRegisterParam authRegisterParam);

    AuthLoginData login(String username, String password);

    void authenticate(String jwtToken);

    void logout();
}
