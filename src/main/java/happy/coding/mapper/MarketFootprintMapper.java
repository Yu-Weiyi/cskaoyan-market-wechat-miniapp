package happy.coding.mapper;

import happy.coding.bean.model.MarketFootprint;
import happy.coding.bean.model.MarketFootprintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketFootprintMapper {
    long countByExample(MarketFootprintExample example);

    int deleteByExample(MarketFootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketFootprint record);

    int insertSelective(MarketFootprint record);

    List<MarketFootprint> selectByExample(MarketFootprintExample example);

    MarketFootprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketFootprint record, @Param("example") MarketFootprintExample example);

    int updateByExample(@Param("record") MarketFootprint record, @Param("example") MarketFootprintExample example);

    int updateByPrimaryKeySelective(MarketFootprint record);

    int updateByPrimaryKey(MarketFootprint record);
}