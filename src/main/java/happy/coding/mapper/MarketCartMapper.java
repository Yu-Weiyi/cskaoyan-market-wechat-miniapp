package happy.coding.mapper;

import happy.coding.bean.model.MarketCart;
import happy.coding.bean.model.MarketCartExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketCartMapper {
    long countByExample(MarketCartExample example);

    int deleteByExample(MarketCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCart record);

    int insertSelective(MarketCart record);

    List<MarketCart> selectByExample(MarketCartExample example);

    MarketCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCart record, @Param("example") MarketCartExample example);

    int updateByExample(@Param("record") MarketCart record, @Param("example") MarketCartExample example);

    int updateByPrimaryKeySelective(MarketCart record);

    int updateByPrimaryKey(MarketCart record);
}