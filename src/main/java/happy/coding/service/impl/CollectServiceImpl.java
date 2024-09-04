package happy.coding.service.impl;

import happy.coding.bean.model.MarketCollectExample;
import happy.coding.mapper.MarketCollectMapper;
import happy.coding.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
