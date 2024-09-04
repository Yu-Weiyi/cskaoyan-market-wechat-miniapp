package happy.coding.service;

import happy.coding.bean.model.MarketBrand;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name BrandService
 * @description Brand service interface.
 * @since 2024-09-03 22:02
 */
public interface BrandService {

    List<MarketBrand> list(int page, int limit);

    MarketBrand selectById(int id);
}
