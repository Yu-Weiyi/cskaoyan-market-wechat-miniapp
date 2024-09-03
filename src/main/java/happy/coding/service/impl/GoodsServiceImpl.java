package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketGoodsExample;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name GoodsServiceImpl
 * @description Goods service implement.
 * @since 2024-09-03 21:45
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    MarketGoodsMapper marketGoodsMapper;

    @Override
    public List<MarketGoods> listNew(int limit) {

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andIsOnSaleEqualTo(true)
                .andIsNewEqualTo(true)
                .andDeletedEqualTo(false);
        marketGoodsExample.setOrderByClause("add_time DESC");
        if (limit > 0) {
            PageHelper.startPage(1, limit);
        }
        List<MarketGoods> marketGoodList = marketGoodsMapper.selectByExample(marketGoodsExample);
        return marketGoodList;
    }

    @Override
    public List<MarketGoods> listHot(int limit) {

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andIsOnSaleEqualTo(true)
                .andIsHotEqualTo(true)
                .andDeletedEqualTo(false);
        marketGoodsExample.setOrderByClause("add_time DESC");
        if (limit > 0) {
            PageHelper.startPage(1, limit);
        }
        List<MarketGoods> marketGoodList = marketGoodsMapper.selectByExample(marketGoodsExample);
        return marketGoodList;
    }

    @Override
    public List<MarketGoods> listByCategoryId(int categoryId, int limit) {

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andCategoryIdEqualTo(categoryId)
                .andIsOnSaleEqualTo(true)
                .andDeletedEqualTo(false);
        marketGoodsExample.setOrderByClause("add_time DESC");
        if (limit > 0) {
            PageHelper.startPage(1, limit);
        }
        List<MarketGoods> marketGoodList = marketGoodsMapper.selectByExample(marketGoodsExample);
        return marketGoodList;
    }
}
