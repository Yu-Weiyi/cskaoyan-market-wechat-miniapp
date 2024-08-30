package happy.coding.mapper;

import happy.coding.bean.model.MarketGoodsProduct;
import happy.coding.bean.model.MarketGoodsProductExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketGoodsProductMapper {
    long countByExample(MarketGoodsProductExample example);

    int deleteByExample(MarketGoodsProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketGoodsProduct record);

    int insertSelective(MarketGoodsProduct record);

    List<MarketGoodsProduct> selectByExample(MarketGoodsProductExample example);

    MarketGoodsProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketGoodsProduct record, @Param("example") MarketGoodsProductExample example);

    int updateByExample(@Param("record") MarketGoodsProduct record, @Param("example") MarketGoodsProductExample example);

    int updateByPrimaryKeySelective(MarketGoodsProduct record);

    int updateByPrimaryKey(MarketGoodsProduct record);
}