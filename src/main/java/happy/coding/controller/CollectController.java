package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.param.CollectAddordeleteParam;
import happy.coding.context.PageInfoContext;
import happy.coding.service.CollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name CollectController
 * @description Collect controller.
 * @since 2024-09-06 20:38
 */
@RestController
@RequestMapping("/collect")
@Tag(name = "收藏接口组", description = "关于用户收藏信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class CollectController {

    @Autowired
    private CollectService collectService;

    @GetMapping("/list")
    @Operation(
            summary = "收藏列表接口", description = "查询收藏列表信息，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-10", description = "查询失败"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo list(@RequestParam Byte type, @RequestParam Integer page, @RequestParam Integer limit) {

        List<?> list = collectService.list(type, page, limit);
        return BaseRespVo.successPage(list, PageInfoContext.getPageInfo());
    }

    @PostMapping("/addordelete")
    @Operation(
            summary = "收藏修改接口", description = "修改收藏状态。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-10", description = "查询失败"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    public BaseRespVo addordelete(@RequestBody CollectAddordeleteParam collectAddordeleteParam) {

        collectService.addordelete(collectAddordeleteParam);
        return BaseRespVo.success();
    }
}
