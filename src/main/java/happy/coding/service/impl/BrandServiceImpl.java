package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.model.MarketBrand;
import happy.coding.bean.model.MarketBrandExample;
import happy.coding.mapper.MarketBrandMapper;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    MarketBrandMapper marketBrandMapper;
    public List<MarketBrand> queryAll(){
        return marketBrandMapper.selectByExample(new MarketBrandExample());
    }

    @Override
    public List<MarketBrand> queryList(int page, int limit) {
        MarketBrandExample marketBrandExample = new MarketBrandExample();
        marketBrandExample.setOrderByClause("add_time");
        PageHelper.startPage(page,limit);
        return marketBrandMapper.selectByExample(marketBrandExample);
    }
}
