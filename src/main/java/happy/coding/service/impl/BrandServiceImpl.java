package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketBrand;
import happy.coding.bean.model.MarketBrandExample;
import happy.coding.mapper.MarketBrandMapper;
import happy.coding.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name BrandServiceImpl
 * @description Brand service implement.
 * @since 2024-09-03 22:02
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    MarketBrandMapper marketBrandMapper;

    @Override
    public List<MarketBrand> list(int limit) {

        MarketBrandExample marketBrandExample = new MarketBrandExample();
        marketBrandExample.createCriteria()
                .andDeletedEqualTo(false);
        marketBrandExample.setOrderByClause("add_time DESC");
        if (limit > 0) {
            PageHelper.startPage(1, limit);
        }
        List<MarketBrand> marketBrandList = marketBrandMapper.selectByExample(marketBrandExample);
        return marketBrandList;
    }

    @Override
    public MarketBrand selectById(int brandId) {

        MarketBrand marketBrand = marketBrandMapper.selectByPrimaryKey(brandId);
        return marketBrand;
    }
}
