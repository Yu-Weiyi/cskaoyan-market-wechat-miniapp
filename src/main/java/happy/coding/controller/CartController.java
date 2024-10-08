package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.CartCheckoutData;
import happy.coding.bean.vo.param.CartAddParam;
import happy.coding.bean.vo.param.CartCheckedParam;
import happy.coding.bean.vo.param.CartFastaddParam;
import happy.coding.bean.vo.param.CartUpdateParam;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.ParamException;
import happy.coding.service.CartService;
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
 * @name CartController
 * @description Cart controller.
 * @since 2024-09-04 16:48
 */
@RestController
@RequestMapping("/cart")
@Tag(name = "购物车接口组", description = "关于用户购物车信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/goodscount")
    @Operation(
            summary = "购物车数量接口", description = "查询购物车内商品数量。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo goodscount() {

        long count = cartService.goodscount();
        return BaseRespVo.success(count);
    }

    @GetMapping("/index")
    @Operation(
            summary = "购物车总览接口", description = "查询购物车总览信息。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo index() {

        Map<String, Object> index = cartService.index();
        return BaseRespVo.success(index);
    }

    @PostMapping("/add")
    @Operation(
            summary = "购物车添加接口", description = "添加商品到用户购物车。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo add(@RequestBody CartAddParam cartAddParam) {

        if (cartAddParam.getGoodsId() == null || cartAddParam.getGoodsId() <=0 ||
                cartAddParam.getProductId() == null || cartAddParam.getProductId() <= 0 ||
                cartAddParam.getNumber() == null || cartAddParam.getNumber() <= 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        long goodscount = cartService.add(cartAddParam);
        return BaseRespVo.success(goodscount);
    }

    @PostMapping("/checked")
    @Operation(
            summary = "购物车勾选接口", description = "勾选用户购物车中的商品。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo checked(@RequestBody CartCheckedParam cartCheckedParam) {

        if (cartCheckedParam.getProductIds() == null || cartCheckedParam.getProductIds().isEmpty() || cartCheckedParam.getIsChecked() == null) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        Map<String, Object> index = cartService.checked(cartCheckedParam);
        return BaseRespVo.success(index);
    }

    @PostMapping("/update")
    @Operation(
            summary = "购物车更新接口", description = "更新用户购物车中的商品数量。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo update(@RequestBody CartUpdateParam cartUpdateParam) {

        if (cartUpdateParam.getId() == null || cartUpdateParam.getId() <= 0 ||
                cartUpdateParam.getNumber() == null || cartUpdateParam.getNumber() <= 0 ||
                cartUpdateParam.getGoodsId() == null || cartUpdateParam.getGoodsId() <= 0 ||
                cartUpdateParam.getProductId() == null || cartUpdateParam.getProductId() <= 0
        ) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        cartService.update(cartUpdateParam);
        return BaseRespVo.success();
    }

    @PostMapping("/delete")
    @Operation(
            summary = "购物车删除接口", description = "删除用户购物车中的商品项。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo delete(@RequestBody Map<String, List<Integer>> map) {

        if (map.get("productIds") == null || map.get("productIds").isEmpty()) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        Map<String, Object> index = cartService.delete(map.get("productIds"));
        return BaseRespVo.success(index);
    }

    @PostMapping("/fastadd")
    @Operation(
            summary = "购物车立即购买接口", description = "立即购买当前商品。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    public BaseRespVo fastadd(@RequestBody CartFastaddParam cartFastaddParam) {

        int cartId = cartService.fastadd(cartFastaddParam);
        return BaseRespVo.success(cartId);
    }

    @GetMapping("/checkout")
    @Operation(
            summary = "购物车检出接口", description = "检出当前购物车信息。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    public BaseRespVo checkout(@RequestParam Integer cartId, @RequestParam Integer addressId, @RequestParam Integer couponId, @RequestParam Integer userCouponId) {

        CartCheckoutData checkout = cartService.checkout(cartId, addressId, couponId, userCouponId);
        return BaseRespVo.success(checkout);
    }
}

