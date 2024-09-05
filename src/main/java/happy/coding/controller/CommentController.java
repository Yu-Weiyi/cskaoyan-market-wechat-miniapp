package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketComment;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.ParamException;
import happy.coding.service.CommentService;
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
 * @name CommentController
 * @description Comment controller.
 * @since 2024-09-05 14:00
 */
@RestController
@RequestMapping("/comment")
@Tag(name = "评论接口组", description = "关于评论信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    @Operation(
            summary = "评论列表接口", description = "查询评论，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误")
            }
    )
    public BaseRespVo list(@RequestParam Integer valueId, @RequestParam Byte type, @RequestParam Byte showType, @RequestParam Integer page, @RequestParam Integer limit) {

        if (valueId == null || valueId <= 0 ||
                type == null || type != 0 && type != 1 ||
                showType == null || showType != 0 && showType != 1 ||
                page == null || page <= 0 ||
                limit == null || limit <= 0
        ) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        List<MarketComment> marketCommentList = commentService.list(valueId, type, page, limit);
        return BaseRespVo.successPage(marketCommentList);
    }

}
