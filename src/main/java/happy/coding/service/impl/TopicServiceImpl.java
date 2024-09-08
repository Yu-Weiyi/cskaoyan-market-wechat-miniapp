package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketTopic;
import happy.coding.bean.model.MarketTopicExample;
import happy.coding.bean.vo.data.TopicDetailData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.QueryException;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.mapper.MarketTopicMapper;
import happy.coding.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name TopicServiceImpl
 * @description Topic service implement.
 * @since 2024-09-03 22:08
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    MarketTopicMapper marketTopicMapper;
    @Autowired
    private MarketGoodsMapper marketGoodsMapper;

    @Override
    public List<MarketTopic> list(int page, int limit) {

        MarketTopicExample marketTopicExample = new MarketTopicExample();
        marketTopicExample.createCriteria()
                .andDeletedEqualTo(false);
        marketTopicExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketTopic> marketTopicList = marketTopicMapper.selectByExample(marketTopicExample);
        return marketTopicList;
    }

    @Override
    public TopicDetailData detail(int topicId) {

        MarketTopic marketTopic = marketTopicMapper.selectByPrimaryKey(topicId);

        if (marketTopic.getDeleted()) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        List<MarketGoods> marketGoodsList = Arrays.stream(marketTopic.getGoods())
                .distinct()
                .map(item -> marketGoodsMapper.selectByPrimaryKey(Integer.valueOf(item)))
                .filter(item -> item != null)
                .filter(item -> !item.getDeleted())
                .toList();
        TopicDetailData topicDetailData = new TopicDetailData(marketTopic, marketGoodsList);
        return topicDetailData;
    }

    @Override
    public List<MarketTopic> related(int topicId) {

        MarketTopic marketTopic = marketTopicMapper.selectByPrimaryKey(topicId);

        if (marketTopic.getDeleted()) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        String title = marketTopic.getTitle();
        String subtitle = marketTopic.getSubtitle();
        // TODO NLP?

        MarketTopicExample marketTopicExample = new MarketTopicExample();
        marketTopicExample.createCriteria()
                .andDeletedEqualTo(false);
        marketTopicExample.setOrderByClause("add_time DESC");
        List<MarketTopic> marketTopicList = marketTopicMapper.selectByExample(marketTopicExample);
        return marketTopicList;
    }
}
