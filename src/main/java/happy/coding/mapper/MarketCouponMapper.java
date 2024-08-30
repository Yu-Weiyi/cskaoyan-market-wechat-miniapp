package happy.coding.mapper;

import happy.coding.bean.model.MarketCoupon;
import happy.coding.bean.model.MarketCouponExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketCouponMapper {
    long countByExample(MarketCouponExample example);

    int deleteByExample(MarketCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCoupon record);

    int insertSelective(MarketCoupon record);

    List<MarketCoupon> selectByExample(MarketCouponExample example);

    MarketCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCoupon record, @Param("example") MarketCouponExample example);

    int updateByExample(@Param("record") MarketCoupon record, @Param("example") MarketCouponExample example);

    int updateByPrimaryKeySelective(MarketCoupon record);

    int updateByPrimaryKey(MarketCoupon record);
}