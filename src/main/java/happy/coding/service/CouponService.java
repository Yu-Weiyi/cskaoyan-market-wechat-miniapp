package happy.coding.service;

import happy.coding.bean.model.MarketCoupon;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name CouponService
 * @description Coupon service interface.
 * @since 2024-09-03 20:22
 */
public interface CouponService {

    List<MarketCoupon> listUserAvailable(int page, int limit);

    List<MarketCoupon> listByStatus(short status, int page, int limit);

    List<MarketCoupon> list(int page, int limit);

    List<MarketCoupon> mylist(short status, int page, int limit);

    void receive(int couponId);

    void exchange(String code);
}
