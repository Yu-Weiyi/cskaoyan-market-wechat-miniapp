package happy.coding.service;

import happy.coding.bean.model.MarketCoupon;

import java.util.List;

public interface CouponService {
    public List<MarketCoupon> queryList(int page , int limit);
}
