package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketCategory;
import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketGoodsExample;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.QueryException;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.service.CategoryService;
import happy.coding.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

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
    @Autowired
    CategoryService categoryService;

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
    public List<MarketGoods> listByCategoryId(int categoryId, int page, int limit) {

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andCategoryIdEqualTo(categoryId)
                .andIsOnSaleEqualTo(true)
                .andDeletedEqualTo(false);
        marketGoodsExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketGoods> marketGoodList = marketGoodsMapper.selectByExample(marketGoodsExample);
        return marketGoodList;
    }

    @Override
    public long count() {

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andIsOnSaleEqualTo(true)
                .andDeletedEqualTo(false);
        return marketGoodsMapper.countByExample(marketGoodsExample);// TODO system cache
    }

    @Override
    public Map<String, Object> category(int categoryId) {

        MarketCategory currentCategory = categoryService.selectById(categoryId);
        int pid = currentCategory.getPid();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        FutureTask<List<MarketCategory>> brotherCategoryTask = new FutureTask<>(() -> categoryService.list(null, pid, 0));
        FutureTask<MarketCategory> parentCategoryTask = new FutureTask<>(() -> categoryService.selectById(pid));

        executorService.submit(brotherCategoryTask);
        executorService.submit(parentCategoryTask);

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("currentCategory", currentCategory);
            map.put("brotherCategory", brotherCategoryTask.get());
            map.put("parentCategory", parentCategoryTask.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }
        return map;
    }
}
