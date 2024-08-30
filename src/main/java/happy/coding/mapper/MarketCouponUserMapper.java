package happy.coding.mapper;

import happy.coding.bean.model.MarketCouponUser;
import happy.coding.bean.model.MarketCouponUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketCouponUserMapper {
    long countByExample(MarketCouponUserExample example);

    int deleteByExample(MarketCouponUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCouponUser record);

    int insertSelective(MarketCouponUser record);

    List<MarketCouponUser> selectByExample(MarketCouponUserExample example);

    MarketCouponUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCouponUser record, @Param("example") MarketCouponUserExample example);

    int updateByExample(@Param("record") MarketCouponUser record, @Param("example") MarketCouponUserExample example);

    int updateByPrimaryKeySelective(MarketCouponUser record);

    int updateByPrimaryKey(MarketCouponUser record);
}