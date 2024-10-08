package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketOrderGoods;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.OrderListData;
import happy.coding.bean.vo.data.OrderSubmitData;
import happy.coding.bean.vo.param.OrderCommentParam;
import happy.coding.bean.vo.param.OrderSubmitParam;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.PageInfoContext;
import happy.coding.exception.ParamException;
import happy.coding.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        if (showType == null || !List.of((byte)0, (byte)1, (byte)2, (byte)3, (byte)4).contains(showType) || page == null || page < 0 || limit == null || limit < 0) {
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

    @PostMapping("/cancel")
    @Operation(
            summary = "订单取消接口", description = "取消用户订单。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo cancel(@RequestBody Map<String, Integer> mapParam) {

        if (mapParam == null || mapParam.get("orderId") == null || mapParam.get("orderId") < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        orderService.cancel(mapParam.get("orderId"));
        return BaseRespVo.success();
    }

    @PostMapping("/refund")
    @Operation(
            summary = "订单退款接口", description = "退款用户订单。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo refund(@RequestBody Map<String, Integer> mapParam) {

        if (mapParam == null || mapParam.get("orderId") == null || mapParam.get("orderId") < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        orderService.refund(mapParam.get("orderId"));
        return BaseRespVo.success();
    }

    @PostMapping("/confirm")
    @Operation(
            summary = "订单确认收货接口", description = "用户确认订单收货。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo confirm(@RequestBody Map<String, Integer> mapParam) {

        if (mapParam == null || mapParam.get("orderId") == null || mapParam.get("orderId") < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        orderService.confirm(mapParam.get("orderId"));
        return BaseRespVo.success();
    }

    @PostMapping("/delete")
    @Operation(
            summary = "订单删除接口", description = "用户删除已完成订单记录。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo delete(@RequestBody Map<String, Integer> mapParam) {

        if (mapParam == null || mapParam.get("orderId") == null || mapParam.get("orderId") < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        orderService.delete(mapParam.get("orderId"));
        return BaseRespVo.success();
    }

    @PostMapping("/submit")
    @Operation(
            summary = "订单提交接口", description = "用户提交订单记录。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo submit(@RequestBody OrderSubmitParam orderSubmitParam) {

        OrderSubmitData submit = orderService.submit(orderSubmitParam);
        return BaseRespVo.success(submit);
    }

    @GetMapping("/goods")
    @Operation(
            summary = "订单评价信息接口", description = "用户准备订单评价。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo goods(@RequestParam Integer orderId, @RequestParam Integer productId) {

        MarketOrderGoods goods = orderService.goods(orderId, productId);
        return BaseRespVo.success(goods);
    }

    @PostMapping("/prepay")
    @Operation(
            summary = "订单付款接口", description = "用户付款订单。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo prepay(@RequestBody Map<String, Integer> mapParam) {

        if (mapParam == null || mapParam.get("orderId") == null || mapParam.get("orderId") <= 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        Integer orderId = mapParam.get("orderId");
        orderService.prepay(orderId);
        return BaseRespVo.success();
    }

    @PostMapping("/comment")
    @Operation(
            summary = "订单评价接口", description = "用户评价订单。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo comment(@RequestBody OrderCommentParam orderCommentParam) {

        orderService.comment(orderCommentParam);
        return BaseRespVo.success();
    }
}

