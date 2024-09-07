package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.param.FeedbackSubmitParam;
import happy.coding.service.FeedbackService;
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
 * @name FeedbackController
 * @description Feedback controller.
 * @since 2024-09-08 01:29
 */
@RestController
@RequestMapping("/feedback")
@Tag(name = "意见反馈接口组", description = "关于用户意见反馈信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    @Operation(
            summary = "用户意见反馈提交接口", description = "用户提交意见反馈。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo submit(@RequestBody FeedbackSubmitParam feedbackSubmitParam) {

        feedbackService.submit(feedbackSubmitParam);
        return BaseRespVo.success();
    }
}
