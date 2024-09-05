package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketAddress;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.param.AddressSaveParam;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.ParamException;
import happy.coding.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    @Operation(
            summary = "收货地址保存接口", description = "保存用户收货地址。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo save(@RequestBody AddressSaveParam addressSaveParam) {

        if (addressSaveParam == null ||
                addressSaveParam.getId() == null ||
                addressSaveParam.getName() == null ||
                addressSaveParam.getTel() == null ||
                addressSaveParam.getAddressDetail() == null ||
                addressSaveParam.getAreaCode() == null ||
                addressSaveParam.getProvince()== null ||
                addressSaveParam.getCity() == null ||
                addressSaveParam.getCounty() == null
        ) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        int newAddressId = addressService.save(addressSaveParam);
        return BaseRespVo.success(newAddressId);
    }

    @GetMapping("/detail")
    @Operation(
            summary = "收货地址详情接口", description = "查询用户收货地址详情。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回"),
                    @ApiResponse(responseCode = "200-1", description = "参数错误"),
                    @ApiResponse(responseCode = "401", description = "认证失败")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo detail(@RequestParam Integer id) {

        if (id == null || id <= 0) {
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
        }

        MarketAddress detail = addressService.detail(id);
        return BaseRespVo.success(detail);
    }
}
