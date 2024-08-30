package happy.coding.mapper;

import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketGoodsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MarketGoodsMapper {
    long countByExample(MarketGoodsExample example);

    int deleteByExample(MarketGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketGoods record);

    int insertSelective(MarketGoods record);

    List<MarketGoods> selectByExample(MarketGoodsExample example);

    MarketGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketGoods record, @Param("example") MarketGoodsExample example);

    int updateByExample(@Param("record") MarketGoods record, @Param("example") MarketGoodsExample example);

    int updateByPrimaryKeySelective(MarketGoods record);

    int updateByPrimaryKey(MarketGoods record);
}