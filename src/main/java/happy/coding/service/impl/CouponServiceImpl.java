package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happy.coding.bean.model.MarketCoupon;
import happy.coding.bean.model.MarketCouponExample;
import happy.coding.mapper.MarketCouponMapper;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    MarketCouponMapper marketCouponMapper;
    //未登录，获取前三条
    public List<MarketCoupon> queryList(int page, int limit){

        PageHelper.startPage( limit,page);

        return marketCouponMapper.selectByExample(new MarketCouponExample());
    }
}
