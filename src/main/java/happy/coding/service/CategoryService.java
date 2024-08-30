package happy.coding.service;

import happy.coding.bean.model.MarketCategory;

import java.util.List;

public interface CategoryService {
    public List<MarketCategory> queryAll();

    List<MarketCategory> queryByLevel(String level);

    List<MarketCategory> queryByLevelWithoutRecommend(String level, int page, int limit);

    List<MarketCategory> queryByPid(Integer pid);
}
