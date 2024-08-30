package happy.coding.mapper;

import happy.coding.bean.model.MarketOrder;
import happy.coding.bean.model.MarketOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketOrderMapper {
    long countByExample(MarketOrderExample example);

    int deleteByExample(MarketOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketOrder record);

    int insertSelective(MarketOrder record);

    List<MarketOrder> selectByExample(MarketOrderExample example);

    MarketOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByExample(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByPrimaryKeySelective(MarketOrder record);

    int updateByPrimaryKey(MarketOrder record);
}