package happy.coding.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import happy.coding.bean.model.MarketStorage;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.OssPutResult;
import happy.coding.client.AliyunClient;
import happy.coding.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.controller
 * @name StorageController
 * @description Stroage controller.
 * @since 2024-09-06 16:20
 */
@RestController
@RequestMapping("/storage")
@Tag(name = "存储接口组", description = "处理存储请求。")
@ApiSupport(author = "WaYease yu_weiyi@outlook.com")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    @Operation(
            summary = "上传接口", description = "上传进行存储。",
            responses = {
                    @ApiResponse(responseCode = "200", description = "正常返回")
            }
    )
    @ApiOperationSupport(author = "于魏祎 yu_weiyi@outlook.com")
    public BaseRespVo upload(MultipartFile file) {

        MarketStorage upload = storageService.upload(file);
        return BaseRespVo.success(upload);
    }
}
