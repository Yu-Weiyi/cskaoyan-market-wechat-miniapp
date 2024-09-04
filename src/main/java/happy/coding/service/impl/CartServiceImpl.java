package happy.coding.service.impl;

import happy.coding.bean.model.MarketCartExample;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketCartMapper;
import happy.coding.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name CartServiceImpl
 * @description Cart service implement.
 * @since 2024-09-04 16:52
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private MarketCartMapper marketCartMapper;

    @Override
    public long goodscount() {

        long count = 0;
        if (UserInfoContext.isLogined()) {
            MarketCartExample marketCartExample = new MarketCartExample();
            marketCartExample.createCriteria()
                    .andUserIdEqualTo(UserInfoContext.getUserId())
                    .andDeletedEqualTo(false);
            count = marketCartMapper.countByExample(marketCartExample);
        }
        return count;
    }
}
