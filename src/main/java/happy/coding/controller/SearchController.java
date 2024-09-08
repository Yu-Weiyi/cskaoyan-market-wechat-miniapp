package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.SearchIndexData;
import happy.coding.service.SearchService;
import happy.coding.service.impl.SearchServiceImpl;
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
 * @name SearchController
 * @description Search controller.
 * @since 2024-09-08 23:04
 */
@RestController
@RequestMapping("/search")
@Tag(name = "搜索接口组", description = "关于搜索信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/index")
    @Operation(
            summary = "搜索总览接口", description = "查询搜索总览。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo index() {

        SearchIndexData index = searchService.index();
        return BaseRespVo.success(index);
    }
    @GetMapping("/helper")
    @Operation(
            summary = "搜索联想接口", description = "查询搜索联想。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo helper(@RequestParam String keyword) {

        List<String> helper = searchService.helper(keyword);
        return BaseRespVo.success(helper);
    }

    @PostMapping("/clearhistory")
    @Operation(
            summary = "搜索历史清除接口", description = "清楚用户搜索历史。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo clearhistory() {

        searchService.clearhistory();
        return BaseRespVo.success();
    }
}
