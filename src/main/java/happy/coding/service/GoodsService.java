package happy.coding.service;

import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketGoodsProduct;
import happy.coding.bean.model.MarketGoodsSpecification;

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

    long count();

    Map<String, Object> category(int categoryId);

    Map<String, Object> detail(int goodsId);

    List<MarketGoods> list(Integer categoryId, Integer brandId, int page, int limit);

    List<MarketGoodsSpecification> selectSpecificationByGoodsId(int goodsId);

    List<MarketGoods> related(int goodsId);

    List<String> helper(String keyword);

    List<MarketGoods> search(String keyword, String sort, String order, int page, int limit);
}
