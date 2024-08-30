package happy.coding.mapper;

import happy.coding.bean.model.MarketSystem;
import happy.coding.bean.model.MarketSystemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MarketSystemMapper {
    long countByExample(MarketSystemExample example);

    int deleteByExample(MarketSystemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketSystem record);

    int insertSelective(MarketSystem record);

    List<MarketSystem> selectByExample(MarketSystemExample example);

    MarketSystem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketSystem record, @Param("example") MarketSystemExample example);

    int updateByExample(@Param("record") MarketSystem record, @Param("example") MarketSystemExample example);

    int updateByPrimaryKeySelective(MarketSystem record);

    int updateByPrimaryKey(MarketSystem record);
}