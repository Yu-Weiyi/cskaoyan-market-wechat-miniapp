package happy.coding.service;

import happy.coding.bean.model.MarketGoods;

import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name GoodsService
 * @description Goods service interface.
 * @since 2024-09-03 21:44
 */
public interface GoodsService {

    List<MarketGoods> listNew(int limit);

    List<MarketGoods> listHot(int limit);

    List<MarketGoods> listByCategoryId(int categoryId, int page, int limit);

    long count();

    Map<String, Object> category(int categoryId);
}
