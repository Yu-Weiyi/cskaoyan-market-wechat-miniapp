package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketAddress;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name AddressController
 * @description Address controller.
 * @since 2024-09-05 23:37
 */
@RestController
@RequestMapping("/address")
@Tag(name = "收货地址接口组", description = "关于收货地址信息。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    @Operation(
            summary = "收货地址列表接口", description = "查询用户收货地址，分页。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo list() {

        List<MarketAddress> list = addressService.list();
        return BaseRespVo.successPage(list);
    }
}
