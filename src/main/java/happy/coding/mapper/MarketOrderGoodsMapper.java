package happy.coding.mapper;

import happy.coding.bean.model.MarketOrderGoods;
import happy.coding.bean.model.MarketOrderGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketOrderGoodsMapper {
    long countByExample(MarketOrderGoodsExample example);

    int deleteByExample(MarketOrderGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketOrderGoods record);

    int insertSelective(MarketOrderGoods record);

    List<MarketOrderGoods> selectByExample(MarketOrderGoodsExample example);

    MarketOrderGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketOrderGoods record, @Param("example") MarketOrderGoodsExample example);

    int updateByExample(@Param("record") MarketOrderGoods record, @Param("example") MarketOrderGoodsExample example);

    int updateByPrimaryKeySelective(MarketOrderGoods record);

    int updateByPrimaryKey(MarketOrderGoods record);
}