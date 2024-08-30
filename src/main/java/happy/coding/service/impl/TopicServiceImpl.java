package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.model.MarketTopic;
import happy.coding.bean.model.MarketTopicExample;
import happy.coding.mapper.MarketTopicMapper;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    MarketTopicMapper marketTopicMapper;
    @Override
    public List<MarketTopic> queryList(int page, int limit) {
        MarketTopicExample marketTopicExample = new MarketTopicExample();
        marketTopicExample.setOrderByClause("add_time");
        PageHelper.startPage(page,limit);
        return marketTopicMapper.selectByExample(marketTopicExample);
    }
}
