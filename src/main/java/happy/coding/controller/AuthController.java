package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.AuthLoginData;
import happy.coding.bean.vo.param.AuthLoginParam;
import happy.coding.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name AuthController
 * @description 用户认证鉴权接口组。
 * @since 2024-09-02 16:40
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "用户认证鉴权接口组", description = "用于用户账户的认证与鉴权。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    // TODO 注册
    public BaseRespVo register() {

        return null;
    }

    @PostMapping("/login")
    @Operation(
            summary = "登录接口", description = "用户账户登录。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "200-6", description = "用户不存在"),
                    @ApiResponse(responseCode = "200-8", description = "用户状态无效"),
                    @ApiResponse(responseCode = "200-9", description = "密码不匹配")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo login(@RequestBody AuthLoginParam authLoginParam) {

        String username = authLoginParam.getUsername();
        String password = authLoginParam.getPassword();
        AuthLoginData authLoginData = authService.login(username, password);
        return BaseRespVo.success(authLoginData);
    }

    // TODO 微信登陆
    public BaseRespVo login_by_weixin() {

        return null;
    }


    @PostMapping("/logout")
    @Operation(
            summary = "登出接口", description = "用户账户登出。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "401", description = "认证失败"),
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo logout() {

        authService.logout();
        return BaseRespVo.success(null);
    }
}
