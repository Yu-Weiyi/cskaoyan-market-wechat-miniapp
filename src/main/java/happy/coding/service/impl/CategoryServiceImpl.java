package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketCategory;
import happy.coding.bean.model.MarketCategoryExample;
import happy.coding.mapper.MarketCategoryMapper;
import happy.coding.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name CategoryServiceImpl
 * @description Categroy service implement.
 * @since 2024-09-03 20:16
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    MarketCategoryMapper marketCategoryMapper;

    @Override
    public List<MarketCategory> listAll(String level) {

        MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
        marketCategoryExample.createCriteria()
                .andLevelEqualTo(level)
                .andDeletedEqualTo(false);
        List<MarketCategory> marketCategoryList = marketCategoryMapper.selectByExample(marketCategoryExample);
        return marketCategoryList;
    }

    @Override
    public List<MarketCategory> list(String level, int pid, int limit) {

        if (level == "L1") {// TODO magic value
            pid = 0;
        }

        MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
        marketCategoryExample.createCriteria()
                .andPidEqualTo(pid)
                .andDeletedEqualTo(false);
        marketCategoryExample.setOrderByClause("add_time DESC");
        if (limit > 0) {
            PageHelper.startPage(1, limit);
        }
        List<MarketCategory> marketCategorieList = marketCategoryMapper.selectByExample(marketCategoryExample);
        return marketCategorieList;
    }

    @Override
    public MarketCategory selectById(int categoryId) {

        return marketCategoryMapper.selectByPrimaryKey(categoryId);
    }
}
