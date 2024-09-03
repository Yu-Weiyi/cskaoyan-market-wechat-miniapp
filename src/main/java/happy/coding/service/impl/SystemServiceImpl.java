package happy.coding.service.impl;

import happy.coding.bean.model.MarketSystem;
import happy.coding.bean.model.MarketSystemExample;
import happy.coding.mapper.MarketSystemMapper;
import happy.coding.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name SystemServiceImpl
 * @description System service implement.
 * @since 2024-09-03 21:38
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    MarketSystemMapper marketSystemMapper;

    @Override
    public Map<String, String> mapAll() {

        MarketSystemExample marketSystemExample = new MarketSystemExample();
        marketSystemExample.createCriteria()
                .andDeletedEqualTo(false);
        List<MarketSystem> marketSystemList = marketSystemMapper.selectByExample(marketSystemExample);// TODO Redis cache

        Map<String, String> map = new HashMap<>();
        marketSystemList.forEach(item -> map.put(item.getKeyName(), item.getKeyValue()));
        return map;
    }
}
