package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo index() {

        Map<String, Object> index = cartService.index();
        return BaseRespVo.success(index);
    }
}
