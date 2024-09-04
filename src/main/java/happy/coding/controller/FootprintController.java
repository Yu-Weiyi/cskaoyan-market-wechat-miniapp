package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketFootprint;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.FootprintListData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.PageInfoContext;
import happy.coding.exception.ParamException;
import happy.coding.service.FootprintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name FootprintController
 * @description Footprint controller.
 * @since 2024-09-04 20:22
 */
@RestController
@RequestMapping("/footprint")
@Tag(name = "用户足迹接口组", description = "关于用户足迹信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class FootprintController {

    @Autowired
    private FootprintService footprintService;


    @GetMapping("/list")
    @Operation(
            summary = "用户足迹列表接口", description = "查询用户足迹，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo list(@RequestParam Integer page, @RequestParam Integer limit) {

        if (page == null || page < 0 || limit == null || limit < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        List<FootprintListData> list = footprintService.list(page, limit);
        return BaseRespVo.successPage(list, PageInfoContext.getPageInfo());
    }
}
