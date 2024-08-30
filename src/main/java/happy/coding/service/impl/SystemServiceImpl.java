package happy.coding.service.impl;

import happy.coding.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.model.MarketSystem;
import happy.coding.bean.model.MarketSystemExample;
import happy.coding.mapper.MarketSystemMapper;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    MarketSystemMapper marketSystemMapper;

    @Override
    public int getNewLimit() {
       return getByKayName("market_wx_index_new");
    }

    @Override
    public int getHotLimit() {
        return getByKayName("market_wx_index_hot");
    }

    @Override
    public int getTopicLimit() {
        return getByKayName("market_wx_index_topic");
    }

    @Override
    public int getBrandLimit() {
        return getByKayName("market_wx_index_brand");
    }

    @Override
    public int getCatLogList(){
        return getByKayName("market_wx_catlog_list");
    }

    @Override
    public int getCatLogGoods(){
        return getByKayName("market_wx_catlog_goods");
    }


    private int getByKayName(String keyName){
        MarketSystemExample marketSystemExample=new MarketSystemExample();
        MarketSystemExample.Criteria criteria= marketSystemExample.createCriteria();
        criteria.andKeyNameEqualTo(keyName);
        MarketSystem marketSystem=marketSystemMapper.selectByExample(marketSystemExample).get(0);
        return Integer.parseInt(marketSystem.getKeyValue());
    }
}
