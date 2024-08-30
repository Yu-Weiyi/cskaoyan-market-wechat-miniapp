package happy.coding.mapper;

import happy.coding.bean.model.MarketRole;
import happy.coding.bean.model.MarketRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketRoleMapper {
    long countByExample(MarketRoleExample example);

    int deleteByExample(MarketRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketRole record);

    int insertSelective(MarketRole record);

    List<MarketRole> selectByExample(MarketRoleExample example);

    MarketRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketRole record, @Param("example") MarketRoleExample example);

    int updateByExample(@Param("record") MarketRole record, @Param("example") MarketRoleExample example);

    int updateByPrimaryKeySelective(MarketRole record);

    int updateByPrimaryKey(MarketRole record);
}