package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.AuthLoginData;
import happy.coding.bean.vo.param.AuthLoginParam;
import happy.coding.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name HomeController
 * @description Home controller.
 * @since 2024-09-03 19:41
 */
@RestController
@RequestMapping("/home")
@Tag(name = "首页接口组", description = "展示首页信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/index")
    @Operation(
            summary = "首页信息接口", description = "展示首页信息。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-10", description = "查询失败"),
                    @ApiResponse(responseCode = "200-11", description = "系统全局参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo index() {

        Map<String, Object> index = homeService.index();
        return BaseRespVo.success(index);
    }
}
