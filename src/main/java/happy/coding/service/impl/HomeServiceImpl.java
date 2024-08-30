package happy.coding.service.impl;

import happy.coding.bean.model.MarketCategory;
import happy.coding.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.vo.data.HomeIndexData;
import happy.coding.bean.vo.data.HomeIndexFloorGoodData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    AdService adService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CouponService couponService;
    @Autowired
    SystemService systemService;
    @Autowired
    BrandService brandService;
    @Autowired
    TopicService topicService;


    @Override
    public HomeIndexData index(Integer userId) {
        //开一个线程池
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        //这是开了是个线程，然后我们可以把不同的任务放在不同的线程里面做

        //先把代码写出来再加线程吧！
        HomeIndexData homeIndexData=new HomeIndexData();
        homeIndexData.setBanner(adService.queryAll());
        homeIndexData.setChannel(categoryService.queryByLevel("L1"));
        homeIndexData.setCouponList(couponService.queryList(1,3));
        homeIndexData.setNewGoodsList(goodsService.queryByNew(1,systemService.getNewLimit()));
        homeIndexData.setHotGoodsList(goodsService.queryByHot(1,systemService.getHotLimit()));
        homeIndexData.setBrandList(brandService.queryList(1,systemService.getBrandLimit()));
        homeIndexData.setTopicList(topicService.queryList(1,systemService.getTopicLimit()));
        //查询类目及其子内容
        homeIndexData.setFloorGoodsList(getCategoryList());

        return homeIndexData;
    }

    private List<HomeIndexFloorGoodData> getCategoryList(){
        List<HomeIndexFloorGoodData> homeIndexFloorGoodDataList=new ArrayList<>();
        List<MarketCategory> marketCategoryList=categoryService.queryByLevelWithoutRecommend("L1",0,systemService.getCatLogList());
        for (MarketCategory marketCategory : marketCategoryList) {
            HomeIndexFloorGoodData homeIndexFloorGoodData = new HomeIndexFloorGoodData();
            List<MarketCategory> marketCategoryL2List = categoryService.queryByPid(marketCategory.getId());
            List<Integer> l2Id=new ArrayList<>();
            if (marketCategoryL2List!=null){
                for (MarketCategory category : marketCategoryL2List) {
                    l2Id.add(category.getId());
                }
            }

            homeIndexFloorGoodData.setName(marketCategory.getName());
            homeIndexFloorGoodData.setId(marketCategory.getId());
            homeIndexFloorGoodData.setGoodsList(goodsService.queryByCategory(l2Id,1,systemService.getCatLogGoods()));
            homeIndexFloorGoodDataList.add(homeIndexFloorGoodData);
        }
        return homeIndexFloorGoodDataList;
    }
}
