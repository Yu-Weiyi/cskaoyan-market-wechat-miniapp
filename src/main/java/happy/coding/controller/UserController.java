package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.UserIndexData;
import happy.coding.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name UserController
 * @description User controller.
 * @since 2024-09-04 09:25
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户接口组", description = "查询用户信息，执行用户操作。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    @Operation(
            summary = "用户总览接口", description = "查询用户总览信息。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo index() {

        UserIndexData order = userService.index();
        Map<String, UserIndexData> map = new HashMap<>();
        map.put("order", order);
        return BaseRespVo.success(map);
    }
}
