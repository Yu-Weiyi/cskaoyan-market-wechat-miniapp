package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.model.MarketCategory;
import happy.coding.bean.model.MarketCategoryExample;
import happy.coding.mapper.MarketCategoryMapper;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    MarketCategoryMapper marketCategoryMapper;
    public List<MarketCategory> queryAll(){
        //查询全部的数据
        return marketCategoryMapper.selectByExample(new MarketCategoryExample());
    }

    @Override
    public List<MarketCategory> queryByLevel(String level) {
        MarketCategoryExample marketCategoryExample=new MarketCategoryExample();
        MarketCategoryExample.Criteria criteria=marketCategoryExample.createCriteria();
        criteria.andLevelEqualTo(level);
        criteria.andDeletedEqualTo(false);
        return marketCategoryMapper.selectByExample(marketCategoryExample);
    }

    @Override
    public List<MarketCategory> queryByLevelWithoutRecommend(String level, int page, int limit) {
        MarketCategoryExample marketCategoryExample=new MarketCategoryExample();
        MarketCategoryExample.Criteria criteria=marketCategoryExample.createCriteria();
        criteria.andLevelEqualTo(level).andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(page,limit);
        return marketCategoryMapper.selectByExample(marketCategoryExample);
    }

    @Override
    public List<MarketCategory> queryByPid(Integer pid) {
        MarketCategoryExample marketCategoryExample=new MarketCategoryExample();
        MarketCategoryExample.Criteria criteria=marketCategoryExample.createCriteria();
        criteria.andPidEqualTo(pid);
        return marketCategoryMapper.selectByExample(marketCategoryExample);
    }

}
