package happy.coding.service;

import happy.coding.bean.model.MarketBrand;

import java.util.List;

public interface BrandService {
    List<MarketBrand> queryAll();

    List<MarketBrand> queryList(int page, int limit);
}
