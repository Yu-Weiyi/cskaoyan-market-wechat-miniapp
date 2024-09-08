package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.*;
import happy.coding.bean.vo.data.SpecificationData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.mapper.MarketGoodsAttributeMapper;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.mapper.MarketGoodsProductMapper;
import happy.coding.mapper.MarketGoodsSpecificationMapper;
import happy.coding.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

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
    private MarketGoodsMapper marketGoodsMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MarketGoodsAttributeMapper marketGoodsAttributeMapper;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private IssueService issueService;
    @Autowired
    private MarketGoodsProductMapper marketGoodsProductMapper;
    @Autowired
    private MarketGoodsSpecificationMapper marketGoodsSpecificationMapper;
    @Autowired
    private SystemService systemService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private FootprintService footprintService;
    @Autowired
    private SearchService searchService;

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

    @Override
    public Map<String, Object> detail(int goodsId) {

        MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(goodsId);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        FutureTask<List<MarketGoodsAttribute>> attributeTask = new FutureTask<>(() -> {
            MarketGoodsAttributeExample marketGoodsAttributeExample = new MarketGoodsAttributeExample();
            marketGoodsAttributeExample.createCriteria()
                    .andGoodsIdEqualTo(goodsId)
                    .andDeletedEqualTo(false);
            List<MarketGoodsAttribute> marketGoodsAttributeList = marketGoodsAttributeMapper.selectByExample(marketGoodsAttributeExample);
            return marketGoodsAttributeList;
        });
        FutureTask<MarketBrand> brandTask = new FutureTask<>(() -> brandService.selectById(marketGoods.getBrandId()));
        FutureTask<Map<String, Object>> commentTask = new FutureTask<>(() -> {
            List<MarketComment> marketCommentList = commentService.list(goodsId, (byte) 0, 0, 0);
            Map<String, Object> map = new HashMap<>();
            map.put("data", marketCommentList);
            map.put("count", marketCommentList.size());
            return map;
        });
        FutureTask<List<MarketIssue>> issueTask = new FutureTask<>(issueService::listAll);
        FutureTask<List<MarketGoodsProduct>> productsListTask = new FutureTask<>(() -> {
            MarketGoodsProductExample marketGoodsProductExample = new MarketGoodsProductExample();
            marketGoodsProductExample.createCriteria()
                    .andGoodsIdEqualTo(goodsId)
                    .andDeletedEqualTo(false);
            List<MarketGoodsProduct> marketGoodsProductList = marketGoodsProductMapper.selectByExample(marketGoodsProductExample);
            return marketGoodsProductList;
        });
        FutureTask<List<SpecificationData>> specificationListTask = new FutureTask<>(() -> {
            MarketGoodsSpecificationExample marketGoodsSpecificationExample = new MarketGoodsSpecificationExample();
            marketGoodsSpecificationExample.createCriteria()
                    .andGoodsIdEqualTo(goodsId)
                    .andDeletedEqualTo(false);
            List<MarketGoodsSpecification> marketGoodsSpecificationList = marketGoodsSpecificationMapper.selectByExample(marketGoodsSpecificationExample);
            Map<String, List<MarketGoodsSpecification>> collect = marketGoodsSpecificationList.stream().collect(Collectors.groupingBy(MarketGoodsSpecification::getSpecification));
            List<String> strings = collect.keySet().stream().collect(Collectors.toList());

            List<SpecificationData> list = new ArrayList<>();
            strings.forEach(item -> list.add(new SpecificationData(item, collect.get(item))));
            return list;
        });
        FutureTask<Boolean> shareTask = new FutureTask<>(() -> {
            String valueStr = systemService.selectByKey("market_wx_share");
            return Boolean.valueOf(valueStr);
        });
        String shareImage = marketGoods.getShareUrl();
        int userHasCollect = 0;
        if (UserInfoContext.isLogined()) {
            userHasCollect = collectService.hasCollectedGoods(goodsId) ? 1 : 0;
        }

        executorService.submit(attributeTask);
        executorService.submit(brandTask);
        executorService.submit(commentTask);
        executorService.submit(issueTask);
        executorService.submit(productsListTask);
        executorService.submit(specificationListTask);
        executorService.submit(shareTask);

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("attribute", attributeTask.get());
            map.put("brand", brandTask.get());
            map.put("comment", commentTask.get());
            map.put("issue", issueTask.get());
            map.put("productList", productsListTask.get());
            map.put("shareImage", shareImage);
            map.put("share", shareTask.get());
            map.put("specificationList", specificationListTask.get());
            map.put("userHasCollect", userHasCollect);
            map.put("info", marketGoods);
        } catch (InterruptedException | ExecutionException e) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        // add footprint
        if (UserInfoContext.isLogined()) {
            footprintService.insert(goodsId);
        }

        return map;
    }

    @Override
    public List<MarketGoods> list(Integer categoryId, Integer brandId, int page, int limit) {

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        MarketGoodsExample.Criteria criteria = marketGoodsExample.createCriteria();
        criteria
                .andIsOnSaleEqualTo(true)
                .andDeletedEqualTo(false);
        if (categoryId != null && categoryId > 0) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (brandId != null && brandId > 0) {
            criteria.andBrandIdEqualTo(brandId);
        }
        marketGoodsExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketGoods> marketGoodList = marketGoodsMapper.selectByExample(marketGoodsExample);
        return marketGoodList;
    }

    @Override
    public List<MarketGoodsSpecification> selectSpecificationByGoodsId(int goodsId) {

        MarketGoodsSpecificationExample marketGoodsSpecificationExample = new MarketGoodsSpecificationExample();
        marketGoodsSpecificationExample.createCriteria()
                .andGoodsIdEqualTo(goodsId)
                .andDeletedEqualTo(false);
        List<MarketGoodsSpecification> marketGoodsSpecificationList = marketGoodsSpecificationMapper.selectByExample(marketGoodsSpecificationExample);
        return marketGoodsSpecificationList;
    }

    @Override
    public List<MarketGoods> related(int goodsId) {

        MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(goodsId);

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andCategoryIdEqualTo(marketGoods.getCategoryId())
                .andIdNotEqualTo(goodsId)
                .andDeletedEqualTo(false);
        PageHelper.startPage(1, 10);
        List<MarketGoods> marketGoodsList = marketGoodsMapper.selectByExample(marketGoodsExample);
        return marketGoodsList;
    }

    @Override
    public List<String> helper(String keyword) {

        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andKeywordsLike("%" + keyword + "%")
                .andDeletedEqualTo(false);
        marketGoodsExample.setOrderByClause("add_time DESC");
        List<MarketGoods> marketGoodsList = marketGoodsMapper.selectByExample(marketGoodsExample);
        return marketGoodsList.stream()
                .map(MarketGoods::getKeywords)
                .toList();
    }

    @Override
    public List<MarketGoods> search(String keyword, String sort, String order, int page, int limit) {

        // search
        MarketGoodsExample marketGoodsExample = new MarketGoodsExample();
        marketGoodsExample.createCriteria()
                .andNameLike("%" + keyword + "%")
                .andDeletedEqualTo(false);
        marketGoodsExample.or()
                .andKeywordsLike("%" + keyword + "%")
                .andDeletedEqualTo(false);
        marketGoodsExample.or()
                .andBriefLike("%" + keyword + "%")
                .andDeletedEqualTo(false);
        marketGoodsExample.setOrderByClause(sort + " " + order);
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketGoods> marketGoodsList = marketGoodsMapper.selectByExample(marketGoodsExample);

        // record search history
        if (UserInfoContext.isLogined()) {
            searchService.addHistory(keyword);
        }

        return marketGoodsList;
    }


}
