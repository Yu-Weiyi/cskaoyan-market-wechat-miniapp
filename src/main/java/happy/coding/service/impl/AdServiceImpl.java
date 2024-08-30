package happy.coding.service.impl;

import happy.coding.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.model.MarketAd;
import happy.coding.bean.model.MarketAdExample;
import happy.coding.mapper.MarketAdMapper;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    MarketAdMapper marketAdMapper;
    public List<MarketAd> queryAll(){
        //查询全部的数据
        return marketAdMapper.selectByExample(new MarketAdExample());
    }
}
