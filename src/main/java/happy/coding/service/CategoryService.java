package happy.coding.service;

import happy.coding.bean.model.MarketCategory;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name CategoryService
 * @description Category service interface.
 * @since 2024-09-03 20:15
 */
public interface CategoryService {

    List<MarketCategory> listAll(String level);

    List<MarketCategory> list(String level, int pid, int limit);

    MarketCategory selectById(int categoryId);
}
