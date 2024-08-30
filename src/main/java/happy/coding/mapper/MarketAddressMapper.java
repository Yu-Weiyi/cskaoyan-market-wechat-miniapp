package happy.coding.mapper;

import happy.coding.bean.model.MarketAddress;
import happy.coding.bean.model.MarketAddressExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketAddressMapper {
    long countByExample(MarketAddressExample example);

    int deleteByExample(MarketAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketAddress record);

    int insertSelective(MarketAddress record);

    List<MarketAddress> selectByExample(MarketAddressExample example);

    MarketAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketAddress record, @Param("example") MarketAddressExample example);

    int updateByExample(@Param("record") MarketAddress record, @Param("example") MarketAddressExample example);

    int updateByPrimaryKeySelective(MarketAddress record);

    int updateByPrimaryKey(MarketAddress record);
}