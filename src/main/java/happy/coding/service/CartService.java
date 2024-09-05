package happy.coding.service;

import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name CartService
 * @description Cart service interface.
 * @since 2024-09-04 16:52
 */
public interface CartService {

    long goodscount();

    Map<String, Object> index();
}
