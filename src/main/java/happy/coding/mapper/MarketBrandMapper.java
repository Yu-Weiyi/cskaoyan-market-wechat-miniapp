package happy.coding.mapper;

import happy.coding.bean.model.MarketBrand;
import happy.coding.bean.model.MarketBrandExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MarketBrandMapper {
    long countByExample(MarketBrandExample example);

    int deleteByExample(MarketBrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketBrand record);

    int insertSelective(MarketBrand record);

    List<MarketBrand> selectByExample(MarketBrandExample example);

    MarketBrand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketBrand record, @Param("example") MarketBrandExample example);

    int updateByExample(@Param("record") MarketBrand record, @Param("example") MarketBrandExample example);

    int updateByPrimaryKeySelective(MarketBrand record);

    int updateByPrimaryKey(MarketBrand record);
}