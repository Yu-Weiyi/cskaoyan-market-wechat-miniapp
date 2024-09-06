package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketCoupon;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.ParamException;
import happy.coding.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name CouponController
 * @description Coupon controller.
 * @since 2024-09-06 02:17
 */
@RestController
@RequestMapping("/coupon")
@Tag(name = "优惠券接口组", description = "关于优惠券信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/list")
    @Operation(
            summary = "优惠券列表接口", description = "查询优惠券列表信息，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo list(@RequestParam Integer page, @RequestParam Integer limit) {

        if (page == null || page <= 0 || limit == null ||limit <= 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        List<MarketCoupon> list;
        if (UserInfoContext.isLogined()) {
            list = couponService.listUserAvailable(page, limit);
            return BaseRespVo.successPage(list);
        } else {
            list = couponService.listByStatus((short) 0, page, limit);
            return BaseRespVo.successPage(list);
        }
    }

    @GetMapping("/mylist")
    @Operation(
            summary = "用户优惠券列表接口", description = "查询用户优惠券列表信息，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo mylist(@RequestParam Short status, @RequestParam Integer page, @RequestParam Integer limit) {

        if (status == null || !List.of((short) 0, (short) 1, (short) 2).contains(status) ||page == null || page <= 0 || limit == null ||limit <= 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        List<MarketCoupon> mylist = couponService.mylist(status, page, limit);
        return BaseRespVo.successPage(mylist);
    }

    @PostMapping("/receive")
    @Operation(
            summary = "用户优惠券领取接口", description = "领取用户优惠券。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo receive(@RequestBody Map<String, Integer> mapParam) {

        Integer couponId = mapParam.get("couponId");

        couponService.receive(couponId);
        return BaseRespVo.success();
    }
}
