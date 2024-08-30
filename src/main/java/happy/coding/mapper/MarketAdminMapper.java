package happy.coding.mapper;

import happy.coding.bean.model.MarketAdmin;
import happy.coding.bean.model.MarketAdminExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketAdminMapper {
    long countByExample(MarketAdminExample example);

    int deleteByExample(MarketAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketAdmin record);

    int insertSelective(MarketAdmin record);

    List<MarketAdmin> selectByExample(MarketAdminExample example);

    MarketAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketAdmin record, @Param("example") MarketAdminExample example);

    int updateByExample(@Param("record") MarketAdmin record, @Param("example") MarketAdminExample example);

    int updateByPrimaryKeySelective(MarketAdmin record);

    int updateByPrimaryKey(MarketAdmin record);
}