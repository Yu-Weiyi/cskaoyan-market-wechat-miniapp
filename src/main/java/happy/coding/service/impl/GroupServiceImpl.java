package happy.coding.service.impl;

import happy.coding.bean.model.MarketGrouponExample;
import happy.coding.mapper.MarketGrouponMapper;
import happy.coding.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name GroupServiceImpl
 * @description Group service implement.
 * @since 2024-09-05 17:10
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private MarketGrouponMapper marketGrouponMapper;

    @Override
    public boolean isGroupIn(int orderId) {

        MarketGrouponExample marketGrouponExample = new MarketGrouponExample();
        marketGrouponExample.createCriteria()
                .andOrderIdEqualTo(orderId)
                .andDeletedEqualTo(false);
        long count = marketGrouponMapper.countByExample(marketGrouponExample);
        return count > 0;
    }
}
