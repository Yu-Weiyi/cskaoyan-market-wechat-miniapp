package happy.coding.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name HealthCheckController
 * @description 服务器健康检查API。
 * @since 2024-08-31 09:31
 */
@RestController
@RequestMapping("/health-check")
@Tag(name = "健康检查接口集", description = "用于检查服务器的多种功能的健康度。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class HealthCheckController {

    /**
     * @name ping
     * @description 用于检查服务器是否正常。
     * @return String
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-08-31, Sat, 10:42, CST
     */
    @GetMapping("/ping")
    @Operation(
            summary = "Ping接口", description = "用于检查服务器是否正常。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo ping() {

        return BaseRespVo.success("OK");
    }

    /**
     * @name druidStat
     * @description 用于检查Druid的服务状态。
     * @return Object
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-08-31, Sat, 10:39, CST
     */
    @GetMapping("/druid/stat")
    @Operation(
            summary = "Druid状态接口", description = "用于检查Druid的服务状态。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo druidStat() {

        return BaseRespVo.success(DruidStatManagerFacade.getInstance().getDataSourceStatDataList());
    }
}
