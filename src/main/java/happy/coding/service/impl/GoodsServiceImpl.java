package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketGoodsExample;
import happy.coding.mapper.MarketGoodsMapper;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    MarketGoodsMapper marketGoodsMapper;
    @Override
    public List<MarketGoods> queryByNew(int page, int limit) {
        return queryByCriteria((UseCriteria<MarketGoodsExample.Criteria>) (example,criteria) -> {
            ((MarketGoodsExample) example).setOrderByClause("add_time");
            criteria.andIsNewEqualTo(true);
            PageHelper.startPage(page,limit);
        });
    }

    @Override
    public List<MarketGoods> queryByHot(int page, int limit) {
        return queryByCriteria((UseCriteria<MarketGoodsExample.Criteria>) (example,criteria) -> {
            ((MarketGoodsExample) example).setOrderByClause("add_time");
            criteria.andIsHotEqualTo(true);
            PageHelper.startPage(page,limit);
        });
    }

    @Override
    public List<MarketGoods> queryByCategory(List<Integer> l2List,int page, int limit){
        return queryByCriteria((UseCriteria<MarketGoodsExample.Criteria>) (example,criteria) -> {
            criteria.andCategoryIdIn(l2List);
            PageHelper.startPage(page,limit);
        });
    }

    private List<MarketGoods> queryByCriteria(UseCriteria useCriteria){
        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria = marketGoodsExample.createCriteria();
        useCriteria.useCriteria(marketGoodsExample,criteria);
        return marketGoodsMapper.selectByExample(marketGoodsExample);
    }
}
@FunctionalInterface
interface UseCriteria<T>{
    void useCriteria(Object example,T criteria);
}
