package happy.coding.service.impl;

import happy.coding.bean.model.MarketAd;
import happy.coding.bean.model.MarketAdExample;
import happy.coding.mapper.MarketAdMapper;
import happy.coding.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name AdServiceImpl
 * @description Ad service implement.
 * @since 2024-09-03 20:00
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    MarketAdMapper marketAdMapper;

    @Override
    public List<MarketAd> listAll() {

        MarketAdExample marketAdExample = new MarketAdExample();
        marketAdExample.createCriteria()
                        .andDeletedEqualTo(false);
        List<MarketAd> marketAdList = marketAdMapper.selectByExample(marketAdExample);
        return marketAdList;
    }
}
