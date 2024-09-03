package happy.coding.service.impl;

import happy.coding.bean.model.MarketCoupon;
import happy.coding.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CouponServiceImplTest {

    @Autowired
    CouponService couponService;

    @Test
    void list() {

        List<MarketCoupon> list = couponService.list(1, 0);
        System.out.println(list);
    }
}