package happy.coding.service;

import happy.coding.bean.model.MarketSystem;

import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name SystemService
 * @description System service interface.
 * @since 2024-09-03 21:37
 */
public interface SystemService {

    Map<String, String> mapAll();

    String selectByKey(String key);
}
