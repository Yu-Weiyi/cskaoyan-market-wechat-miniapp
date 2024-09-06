package happy.coding.service;

import happy.coding.bean.model.MarketStorage;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name StorageService
 * @description Stroage service interface.
 * @since 2024-09-06 16:23
 */
public interface StorageService {

    MarketStorage upload(MultipartFile file);
}
