package happy.coding.service;

import happy.coding.bean.vo.data.AuthLoginData;

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

    /**
     * @name login
     * @description 登录。
     * @param username java.lang.String
     * @param password java.lang.String
     * @return AuthLoginData
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 19:14, CST
     */
    AuthLoginData login(String username, String password);
}
