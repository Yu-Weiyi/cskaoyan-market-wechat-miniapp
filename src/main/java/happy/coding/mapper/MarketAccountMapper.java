package happy.coding.mapper;

import happy.coding.bean.model.MarketAccount;
import happy.coding.bean.model.MarketAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketAccountMapper {
    long countByExample(MarketAccountExample example);

    int deleteByExample(MarketAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketAccount record);

    int insertSelective(MarketAccount record);

    List<MarketAccount> selectByExample(MarketAccountExample example);

    MarketAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketAccount record, @Param("example") MarketAccountExample example);

    int updateByExample(@Param("record") MarketAccount record, @Param("example") MarketAccountExample example);

    int updateByPrimaryKeySelective(MarketAccount record);

    int updateByPrimaryKey(MarketAccount record);
}