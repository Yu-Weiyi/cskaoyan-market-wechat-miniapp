package happy.coding.mapper;

import happy.coding.bean.model.MarketUser;
import happy.coding.bean.model.MarketUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketUserMapper {
    long countByExample(MarketUserExample example);

    int deleteByExample(MarketUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketUser record);

    int insertSelective(MarketUser record);

    List<MarketUser> selectByExample(MarketUserExample example);

    MarketUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketUser record, @Param("example") MarketUserExample example);

    int updateByExample(@Param("record") MarketUser record, @Param("example") MarketUserExample example);

    int updateByPrimaryKeySelective(MarketUser record);

    int updateByPrimaryKey(MarketUser record);
}