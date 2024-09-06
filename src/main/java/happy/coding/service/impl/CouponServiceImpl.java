package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.*;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.mapper.MarketCartMapper;
import happy.coding.mapper.MarketCouponMapper;
import happy.coding.mapper.MarketCouponUserMapper;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private MarketCouponMapper marketCouponMapper;
    @Autowired
    private MarketCouponUserMapper marketCouponUserMapper;
    @Autowired
    private MarketCartMapper marketCartMapper;
    @Autowired
    private MarketGoodsMapper marketGoodsMapper;

    @Override
    public List<MarketCoupon> listUserAvailable(int page, int limit) {

        Integer userId = UserInfoContext.getUserId();
        Date now = new Date();

        List<MarketCoupon> marketCouponList = listByStatus((short) 0, 1, 0);// TODO 引入Redis缓存机制 使用分次查询
        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        marketCouponUserExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andStartTimeLessThanOrEqualTo(now)
                .andEndTimeGreaterThanOrEqualTo(now)
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
    public List<MarketCoupon> listByStatus(short status, int page, int limit) {

        MarketCouponExample marketCouponExample = new MarketCouponExample();
        marketCouponExample.createCriteria()
                .andStatusEqualTo(status)
                .andDeletedEqualTo(false);
        marketCouponExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketCoupon> marketCouponList = marketCouponMapper.selectByExample(marketCouponExample);
        return marketCouponList;
    }

    @Override
    public List<MarketCoupon> list(int page, int limit) {

        List<MarketCoupon> list;
        if (UserInfoContext.isLogined()) {
            list = listUserAvailable(page, limit);
        } else {
            list = listByStatus((short) 0, page, limit);
        }
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public List<MarketCoupon> mylist(short status, int page, int limit) {

        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        marketCouponUserExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andStatusEqualTo(status)
                .andDeletedEqualTo(false);
        marketCouponUserExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketCouponUser> marketCouponUserList = marketCouponUserMapper.selectByExample(marketCouponUserExample);
        List<Integer> couponIdList = marketCouponUserList.stream().map(item -> item.getCouponId()).toList();

        if (couponIdList == null || couponIdList.isEmpty()) {
            return new ArrayList<>();
        }

        MarketCouponExample marketCouponExample = new MarketCouponExample();
        marketCouponExample.createCriteria()
                .andIdIn(couponIdList)
                .andDeletedEqualTo(false);
        List<MarketCoupon> marketCouponList = marketCouponMapper.selectByExample(marketCouponExample);
        return marketCouponList;
    }

    @Override
    public void receive(int couponId) {

        int userId = UserInfoContext.getUserId();
        Date now = new Date();
        MarketCoupon marketCoupon = marketCouponMapper.selectByPrimaryKey(couponId);

        if (marketCoupon == null) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        if (marketCoupon.getType() != 0) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        if (marketCoupon.getStatus() != 0) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        if (marketCoupon.getEndTime() != null && marketCoupon.getEndTime().before(now)) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        if (marketCoupon.getDeleted()) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        MarketCouponUserExample.Criteria criteria = marketCouponUserExample.createCriteria();
        criteria
                .andCouponIdEqualTo(couponId)
                .andDeletedEqualTo(false);
        long countAllUser = marketCouponUserMapper.countByExample(marketCouponUserExample);
        if (marketCoupon.getTotal() <= countAllUser) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        criteria.andUserIdEqualTo(userId);
        long countThisUser = marketCouponUserMapper.countByExample(marketCouponUserExample);
        if (marketCoupon.getLimit() <= countThisUser) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        // check pass
        MarketCouponUser marketCouponUser = new MarketCouponUser();
        marketCouponUser.setUserId(userId);
        marketCouponUser.setCouponId(couponId);
        marketCouponUser.setStatus((short) 0);
        marketCouponUser.setStartTime(marketCoupon.getStartTime());
        marketCouponUser.setEndTime(marketCoupon.getEndTime());
        marketCouponUser.setAddTime(now);
        marketCouponUser.setUpdateTime(now);
        marketCouponUser.setDeleted(false);
        marketCouponUserMapper.insertSelective(marketCouponUser);
    }

    @Override
    public void exchange(String code) {

        MarketCouponExample marketCouponExample = new MarketCouponExample();
        marketCouponExample.createCriteria()
                .andTypeEqualTo((short) 2)
                .andStatusEqualTo((short) 0)
                .andCodeEqualTo(code)
                .andDeletedEqualTo(false);
        List<MarketCoupon> marketCouponList = marketCouponMapper.selectByExample(marketCouponExample);

        if (marketCouponList == null || marketCouponList.isEmpty()) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        MarketCoupon marketCoupon = marketCouponList.get(0);
        int userId = UserInfoContext.getUserId();
        Date now = new Date();
        int couponId = marketCoupon.getId();

        if (marketCoupon.getEndTime() != null && marketCoupon.getEndTime().before(now)) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        if (marketCoupon.getDeleted()) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        MarketCouponUserExample.Criteria criteria = marketCouponUserExample.createCriteria();
        criteria
                .andCouponIdEqualTo(couponId)
                .andDeletedEqualTo(false);
        long countAllUser = marketCouponUserMapper.countByExample(marketCouponUserExample);
        if (marketCoupon.getTotal() <= countAllUser) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        criteria.andUserIdEqualTo(userId);
        long countThisUser = marketCouponUserMapper.countByExample(marketCouponUserExample);
        if (marketCoupon.getLimit() <= countThisUser) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        // check pass
        MarketCouponUser marketCouponUser = new MarketCouponUser();
        marketCouponUser.setUserId(userId);
        marketCouponUser.setCouponId(couponId);
        marketCouponUser.setStatus((short) 0);
        marketCouponUser.setStartTime(marketCoupon.getStartTime());
        marketCouponUser.setEndTime(marketCoupon.getEndTime());
        marketCouponUser.setAddTime(now);
        marketCouponUser.setUpdateTime(now);
        marketCouponUser.setDeleted(false);
        marketCouponUserMapper.insertSelective(marketCouponUser);
    }

    @Override
    public List<MarketCoupon> selectlist(int cartId) {

        MarketCart marketCart = marketCartMapper.selectByPrimaryKey(cartId);
        if (marketCart == null) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }
        MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(marketCart.getGoodsId());


        Date now = new Date();
        MarketCouponUserExample marketCouponUserExample = new MarketCouponUserExample();
        marketCouponUserExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andStatusEqualTo((short) 0)
                .andDeletedEqualTo(false);
        List<MarketCouponUser> marketCouponUserList = marketCouponUserMapper.selectByExample(marketCouponUserExample);
        List<MarketCoupon> marketCouponList = marketCouponUserList.stream()
                .map(item -> item.getCouponId())
                .distinct()
                .map(item -> marketCouponMapper.selectByPrimaryKey(item))
                .filter(item -> item.getType().equals((short)0))
                .filter(item -> {
                    switch (item.getGoodsType()) {
                        case 0:
                            return true;
                        case 1:
                            return item.getGoodsValue().contains(marketGoods.getCategoryId().toString());
                        case 2:
                            return item.getGoodsValue().contains(marketGoods.getId().toString());
                        default:
                            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
                    }
                })
                .filter(item -> {
                    switch (item.getTimeType()) {
                        case 0:
                            return true;// FIXME hard
                        case 1:
                            return now.after(item.getStartTime()) && now.before(item.getEndTime());
                        default:
                            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
                    }
                })
                .filter(item -> !item.getDeleted())
                .toList();

        return marketCouponList;
    }
}
