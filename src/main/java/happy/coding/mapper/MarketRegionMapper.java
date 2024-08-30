package happy.coding.mapper;

import happy.coding.bean.model.MarketRegion;
import happy.coding.bean.model.MarketRegionExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketRegionMapper {
    long countByExample(MarketRegionExample example);

    int deleteByExample(MarketRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketRegion record);

    int insertSelective(MarketRegion record);

    List<MarketRegion> selectByExample(MarketRegionExample example);

    MarketRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketRegion record, @Param("example") MarketRegionExample example);

    int updateByExample(@Param("record") MarketRegion record, @Param("example") MarketRegionExample example);

    int updateByPrimaryKeySelective(MarketRegion record);

    int updateByPrimaryKey(MarketRegion record);
}