package happy.coding.service;

import happy.coding.bean.model.MarketGoods;

import java.util.List;

public interface GoodsService {
    List<MarketGoods> queryByNew(int page, int limit);

    List<MarketGoods> queryByHot(int page, int limit);

    List<MarketGoods> queryByCategory(List<Integer> l2List, int page, int limit);
}
