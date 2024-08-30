package happy.coding.mapper;

import happy.coding.bean.model.MarketLog;
import happy.coding.bean.model.MarketLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketLogMapper {
    long countByExample(MarketLogExample example);

    int deleteByExample(MarketLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketLog record);

    int insertSelective(MarketLog record);

    List<MarketLog> selectByExample(MarketLogExample example);

    MarketLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketLog record, @Param("example") MarketLogExample example);

    int updateByExample(@Param("record") MarketLog record, @Param("example") MarketLogExample example);

    int updateByPrimaryKeySelective(MarketLog record);

    int updateByPrimaryKey(MarketLog record);
}