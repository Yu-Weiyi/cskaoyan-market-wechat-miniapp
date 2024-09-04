package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name CatalogController
 * @description Catalog controller.
 * @since 2024-09-04 10:43
 */
@RestController
@RequestMapping("/catalog")
@Tag(name = "目录接口组", description = "关于目录信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @GetMapping("/index")
    @Operation(
            summary = "目录总览接口", description = "查询目录信息。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-10", description = "查询失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo index() {

        Map<String, Object> index = catalogService.index();
        return BaseRespVo.success(index);
    }
}
