package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketTopic;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.TopicDetailData;
import happy.coding.service.TopicService;
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
 * @name TopicController
 * @description Topic controller.
 * @since 2024-09-08 02:13
 */
@RestController
@RequestMapping("/topic")
@Tag(name = "专题接口组", description = "关于专题信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("/list")
    @Operation(
            summary = "专题列表接口", description = "查询专题列表。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo list(@RequestParam Integer page, @RequestParam Integer limit) {

        List<MarketTopic> marketTopicList = topicService.list(page, limit);
        return BaseRespVo.successPage(marketTopicList);
    }

    @GetMapping("/detail")
    @Operation(
            summary = "专题详情接口", description = "查询专题详情。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo detail(@RequestParam Integer id) {

        TopicDetailData detail = topicService.detail(id);
        return BaseRespVo.success(detail);
    }

    @GetMapping("/related")
    @Operation(
            summary = "专题详情接口", description = "查询专题详情。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo related(@RequestParam Integer id) {

        List<MarketTopic> related = topicService.related(id);
        return BaseRespVo.successPage(related);
    }
}
