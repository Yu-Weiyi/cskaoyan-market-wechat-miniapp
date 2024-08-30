package happy.coding.service;

import happy.coding.bean.model.MarketTopic;

import java.util.List;

public interface TopicService {
    List<MarketTopic> queryList(int page, int limit);
}
