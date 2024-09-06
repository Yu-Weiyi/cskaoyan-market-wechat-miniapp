package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import happy.coding.bean.model.*;
import happy.coding.context.PageInfoContext;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketCollectMapper;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.mapper.MarketTopicMapper;
import happy.coding.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name CollectServiceImpl
 * @description Collect service implement.
 * @since 2024-09-04 16:16
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private MarketCollectMapper marketCollectMapper;
    @Autowired
    private MarketGoodsMapper marketGoodsMapper;
    @Autowired
    private MarketTopicMapper marketTopicMapper;

    private boolean hasCollected(byte type, int valueId) {

        MarketCollectExample marketCollectExample = new MarketCollectExample();
        marketCollectExample.createCriteria()
                .andTypeEqualTo(type)
                .andValueIdEqualTo(valueId)
                .andDeletedEqualTo(false);
        long count = marketCollectMapper.countByExample(marketCollectExample);
        return count > 0;
    }

    @Override
    public boolean hasCollectedGoods(int goodsId) {

        return hasCollected((byte) 0, goodsId);
    }

    @Override
    public boolean hasCollectedTopic(int topicId) {

        return hasCollected((byte) 1, topicId);
    }

    @Override
    public List<? extends Object> list(byte type, int page, int limit) {

        MarketCollectExample marketCollectExample = new MarketCollectExample();
        marketCollectExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andTypeEqualTo(type)
                .andDeletedEqualTo(false);
        marketCollectExample.setOrderByClause("add_time desc");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketCollect> marketCollectList = marketCollectMapper.selectByExample(marketCollectExample);
        PageInfoContext.serPageInfo(PageInfo.of(marketCollectList));

        switch (type) {
            case 0:
                List<MarketGoods> marketGoodsList = marketCollectList.stream()
                        .map(item -> {
                            MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(item.getValueId());
                            return marketGoods;
                        })
                        .toList();
                return marketGoodsList;
            case 1:
                List<MarketTopic> marketTopicList = marketCollectList.stream()
                        .map(item -> {
                            MarketTopic marketTopic = marketTopicMapper.selectByPrimaryKey(item.getValueId());
                            return marketTopic;
                        })
                        .toList();
                return marketTopicList;
            default:
                return new ArrayList<>();
        }
    }
}
