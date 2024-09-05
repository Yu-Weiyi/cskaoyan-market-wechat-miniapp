package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketAddress;
import happy.coding.bean.model.MarketAddressExample;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketAddressMapper;
import happy.coding.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name AddressServiceImpl
 * @description Address service implement.
 * @since 2024-09-05 23:47
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private MarketAddressMapper marketAddressMapper;

    @Override
    public List<MarketAddress> list() {

        MarketAddressExample marketAddressExample = new MarketAddressExample();
        marketAddressExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andDeletedEqualTo(false);
        PageHelper.startPage(1, 10);
        List<MarketAddress> marketAddressList = marketAddressMapper.selectByExample(marketAddressExample);
        if (marketAddressList == null || marketAddressList.isEmpty()) {
            return new ArrayList<>();
        }
        MarketAddress defaultAddress = marketAddressList.stream().filter(item -> item.getIsDefault()).findFirst().orElse(null);
        marketAddressList.remove(defaultAddress);
        marketAddressList.set(0, defaultAddress);
        return marketAddressList;
    }
}
