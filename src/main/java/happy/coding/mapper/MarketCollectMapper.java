package happy.coding.mapper;

import happy.coding.bean.model.MarketCollect;
import happy.coding.bean.model.MarketCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketCollectMapper {
    long countByExample(MarketCollectExample example);

    int deleteByExample(MarketCollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCollect record);

    int insertSelective(MarketCollect record);

    List<MarketCollect> selectByExample(MarketCollectExample example);

    MarketCollect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCollect record, @Param("example") MarketCollectExample example);

    int updateByExample(@Param("record") MarketCollect record, @Param("example") MarketCollectExample example);

    int updateByPrimaryKeySelective(MarketCollect record);

    int updateByPrimaryKey(MarketCollect record);
}