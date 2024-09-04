package happy.coding.service.impl;

import happy.coding.bean.vo.data.HomeIndexFloorGoodsListData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
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
    public Map<String, Object> index() {// TODO CanAuthInterceptor

        Map<String, String> systemMap = systemService.mapAll();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        FutureTask<List> bannerTask = new FutureTask<>(adService::listAll);
        FutureTask<List> channelTask = new FutureTask<>(() -> categoryService.listAll("L1"));// TODO magic value
        FutureTask<List> couponListTask = new FutureTask<>(
                UserInfoContext.isLogined() ?
                        () -> couponService.listUserAvailable(3) :
                        () -> couponService.list(1, 3)
        );
        FutureTask<List> newGoodsListTask = new FutureTask<>(() -> goodsService.listHot(Integer.parseInt(systemMap.get("market_wx_index_hot"))));
        FutureTask<List> hotGoodsListTask = new FutureTask<>(() -> goodsService.listNew(Integer.parseInt(systemMap.get("market_wx_index_new"))));
        FutureTask<List> brandListTask = new FutureTask<>(() -> brandService.list(Integer.parseInt(systemMap.get("market_wx_index_brand"))));
        FutureTask<List> topicListTask = new FutureTask<>(() -> topicService.list(Integer.parseInt(systemMap.get("market_wx_index_topic"))));
        FutureTask<List> floorGoodsListTask = new FutureTask<>(
                () -> {
                    List<Object> collect = categoryService.list("L1", 0, Integer.parseInt(systemMap.get("market_wx_catlog_list"))).stream()// TODO magic value
                            .map(category -> new HomeIndexFloorGoodsListData(
                                    category.getId(),
                                    category.getName(),
                                    goodsService.listByCategoryId(category.getId(), 1, Integer.parseInt(systemMap.get("market_wx_catlog_goods")))))
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
