package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.OrderListData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.PageInfoContext;
import happy.coding.exception.ParamException;
import happy.coding.service.OrderService;
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
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name OrderController
 * @description Order controller.
 * @since 2024-09-05 16:21
 */
@RestController
@RequestMapping("/order")
@Tag(name = "订单接口组", description = "关于订单信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    @Operation(
            summary = "订单列表接口", description = "查询用户订单，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo list(@RequestParam Byte showType, @RequestParam Integer page, @RequestParam Integer limit) {

        if (showType == null || showType != 0 && showType != 1 ||page == null || page < 0 || limit == null || limit < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        List<OrderListData> list = orderService.list(showType, page, limit);
        return BaseRespVo.successPage(list, PageInfoContext.getPageInfo());
    }

    @GetMapping("/detail")
    @Operation(
            summary = "订单详情接口", description = "查询用户订单详情。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo detail(@RequestParam Integer orderId) {

        if (orderId == null || orderId < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        Map<String, Object> detail = orderService.detail(orderId);
        return BaseRespVo.success(detail);
    }
}
