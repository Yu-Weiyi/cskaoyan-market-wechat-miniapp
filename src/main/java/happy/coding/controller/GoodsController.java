package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.ParamException;
import happy.coding.service.GoodsService;
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

    @GetMapping("/list")
    @Operation(
            summary = "商品列表接口", description = "查询指定类别在售商品列表，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo list(@RequestParam Integer categoryId, @RequestParam Integer page, @RequestParam Integer limit) {

        if (categoryId == null || categoryId < 0 || page == null || page < 0 || limit == null || limit < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        List<MarketGoods> marketGoodsList = goodsService.listByCategoryId(categoryId, page, limit);
        return BaseRespVo.successPage(marketGoodsList);
    }

    @GetMapping("/category")
    @Operation(
            summary = "商品类别接口", description = "查询指定商品类别。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "200-10", description = "查询失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo category(@RequestParam Integer id) {

        if (id == null || id < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        Map<String, Object> category = goodsService.category(id);
        return BaseRespVo.success(category);
    }

    @GetMapping("/detail")
    @Operation(
            summary = "商品详情接口", description = "查询指定商品详情。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "200-10", description = "查询失败"),
                    @ApiResponse(responseCode = "200-11", description = "系统全局参数错误")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo detail(@RequestParam Integer id) {

        if (id == null || id < 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        Map<String, Object> detail = goodsService.detail(id);
        return BaseRespVo.success(detail);
    }
}
