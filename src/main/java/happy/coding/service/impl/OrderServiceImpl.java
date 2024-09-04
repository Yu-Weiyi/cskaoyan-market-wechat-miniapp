package happy.coding.service.impl;

import happy.coding.bean.model.MarketOrderExample;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketOrderMapper;
import happy.coding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name OrderServiceImpl
 * @description Order interface implement.
 * @since 2024-09-04 10:10
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    MarketOrderMapper marketOrderMapper;

    @Override
    public long countByStatus(short status) {

        MarketOrderExample marketOrderExample = new MarketOrderExample();
        marketOrderExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andOrderStatusEqualTo(status)
                .andDeletedEqualTo(false);
        long num = marketOrderMapper.countByExample(marketOrderExample);
        return num;
    }

    @Override
    public long countUnpaid() {

        return countByStatus((short) 101);

    }

    @Override
    public long countUnShip() {

        return countByStatus((short) 201);
    }

    @Override
    public long countUnrecv() {

        return countByStatus((short) 101);
    }

    @Override
    public long countUnComment() {

        return countByStatus((short) 101);
    }
}
