package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name GoodsController
 * @description Goods controller.
 * @since 2024-09-04 09:05
 */
@RestController
@RequestMapping("/goods")
@Tag(name = "商品接口组", description = "关于商品信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/count")
    @Operation(
            summary = "商品数接口", description = "查询在售商品数量。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo count() {

        long count = goodsService.count();
        return BaseRespVo.success(count);
    }
}
