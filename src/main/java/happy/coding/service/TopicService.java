package happy.coding.service;

import happy.coding.bean.model.MarketTopic;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name TopicService
 * @description Topic service interface.
 * @since 2024-09-03 22:07
 */
public interface TopicService {

    List<MarketTopic> list(int limit);
}
