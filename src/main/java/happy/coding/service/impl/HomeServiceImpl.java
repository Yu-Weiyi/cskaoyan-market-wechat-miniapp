package happy.coding.service.impl;

import happy.coding.bean.model.MarketBrand;
import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketTopic;
import happy.coding.bean.vo.data.HomeIndexFloorGoodsListData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.exception.SystemException;
import happy.coding.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name HomeServiceImpl
 * @description Home service implement.
 * @since 2024-09-03 19:51
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    SystemService systemService;
    @Autowired
    AdService adService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CouponService couponService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    BrandService brandService;
    @Autowired
    TopicService topicService;

    @Override
    public Map<String, Object> index() {

        Map<String, String> systemMap = systemService.mapAll();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        FutureTask<List> bannerTask = new FutureTask<>(adService::listAll);
        FutureTask<List> channelTask = new FutureTask<>(() -> categoryService.listAll("L1"));// TODO magic value
        FutureTask<List> couponListTask = new FutureTask<>(
                UserInfoContext.isLogined() ?
                        () -> couponService.listUserAvailable(1, 3) :
                        () -> couponService.listByStatus((short) 0, 1, 3)
        );
        FutureTask<List> newGoodsListTask = new FutureTask<>(() -> {
            String value = systemMap.get("market_wx_index_hot");
            if (value == null) {
                throw new SystemException(ErrorCodeConstant.SYSTEM_GLOBAL_PARAM_ERROR);
            }
            List<MarketGoods> marketGoodsList = goodsService.listHot(Integer.parseInt(value));
            return marketGoodsList;
        });
        FutureTask<List> hotGoodsListTask = new FutureTask<>(() -> {
            String value = systemMap.get("market_wx_index_new");
            if (value == null) {
                throw new SystemException(ErrorCodeConstant.SYSTEM_GLOBAL_PARAM_ERROR);
            }
            List<MarketGoods> marketGoodsList = goodsService.listNew(Integer.parseInt(value));
            return marketGoodsList;
        });
        FutureTask<List> brandListTask = new FutureTask<>(() -> {
            String value = systemMap.get("market_wx_index_brand");
            if (value == null) {
                throw new SystemException(ErrorCodeConstant.SYSTEM_GLOBAL_PARAM_ERROR);
            }
            List<MarketBrand> marketBrandList = brandService.list(1, Integer.parseInt(value));
            return marketBrandList;
        });
        FutureTask<List> topicListTask = new FutureTask<>(() -> {
            String value = systemMap.get("market_wx_index_topic");
            if (value == null) {
                throw new SystemException(ErrorCodeConstant.SYSTEM_GLOBAL_PARAM_ERROR);
            }
            List<MarketTopic> marketTopicList = topicService.list(Integer.parseInt(value));
            return marketTopicList;
        });
        FutureTask<List> floorGoodsListTask = new FutureTask<>(
                () -> {
                    String value0 = systemMap.get("market_wx_catlog_list");
                    String value1 = systemMap.get("market_wx_catlog_goods");
                    if (value0 == null || value1 == null) {
                        throw new SystemException(ErrorCodeConstant.SYSTEM_GLOBAL_PARAM_ERROR);
                    }
                    List<Object> collect = categoryService.list("L1", 0, Integer.parseInt(value0)).stream()// TODO magic value
                            .map(category -> new HomeIndexFloorGoodsListData(
                                    category.getId(),
                                    category.getName(),
                                    goodsService.list(category.getId(), null, 1, Integer.parseInt(value1))))
                            .collect(Collectors.toList());
                    return collect;
                }
        );

        executorService.submit(bannerTask);
        executorService.submit(channelTask);
        executorService.submit(couponListTask);
        executorService.submit(newGoodsListTask);
        executorService.submit(hotGoodsListTask);
        executorService.submit(brandListTask);
        executorService.submit(topicListTask);
        executorService.submit(floorGoodsListTask);

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("banner", bannerTask.get());
            map.put("channel", channelTask.get());
            map.put("couponList", couponListTask.get());
            map.put("newGoodsList", newGoodsListTask.get());
            map.put("hotGoodsList", hotGoodsListTask.get());
            map.put("brandList", brandListTask.get());
            map.put("topicList", topicListTask.get());
            map.put("floorGoodsList", floorGoodsListTask.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }
        return map;
    }
}
