package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketCoupon;
import happy.coding.bean.model.MarketCouponExample;
import happy.coding.bean.model.MarketCouponUser;
import happy.coding.bean.model.MarketCouponUserExample;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketCouponMapper;
import happy.coding.mapper.MarketCouponUserMapper;
import happy.coding.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name CouponServiceImpl
 * @description Coupon service implement.
 * @since 2024-09-03 20:23
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    MarketCouponMapper marketCouponMapper;
    @Autowired
    MarketCouponUserMapper marketCouponUserMapper;

    @Override
    public List<MarketCoupon> listUserAvailable(int limit) {

        Integer userId = UserInfoContext.getUserId();
        List<MarketCoupon> marketCouponList = listAll();// TODO 引入Redis缓存机制 使用分次查询
        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        marketCouponUserExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andDeletedEqualTo(false);
        List<MarketCouponUser> marketCouponUserList = marketCouponUserMapper.selectByExample(marketCouponUserExample);
        List<Integer> alreadyGotCouponIds = marketCouponUserList.stream()
                .map(MarketCouponUser::getCouponId)
                .toList();
        List<MarketCoupon> avaliableCouponList = marketCouponList.stream()
                .filter(item -> !alreadyGotCouponIds.contains(item.getId()))
                .limit(limit)
                .toList();
        return avaliableCouponList;
    }

    @Override
    public List<MarketCoupon> list(int page, int limit) {

        MarketCouponExample marketCouponExample = new MarketCouponExample();
        marketCouponExample.createCriteria()
                .andStatusEqualTo((short) 0)
                .andDeletedEqualTo(false);
        marketCouponExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketCoupon> marketCouponList = marketCouponMapper.selectByExample(marketCouponExample);
        return marketCouponList;
    }

    @Override
    public List<MarketCoupon> list(int limit) {

        return list(1, limit);
    }

    @Override
    public List<MarketCoupon> listAll() {

        return list(1, 0);
    }
}
