package happy.coding.mapper;

import happy.coding.bean.model.MarketTopic;
import happy.coding.bean.model.MarketTopicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MarketTopicMapper {
    long countByExample(MarketTopicExample example);

    int deleteByExample(MarketTopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketTopic record);

    int insertSelective(MarketTopic record);

    List<MarketTopic> selectByExample(MarketTopicExample example);

    MarketTopic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketTopic record, @Param("example") MarketTopicExample example);

    int updateByExample(@Param("record") MarketTopic record, @Param("example") MarketTopicExample example);

    int updateByPrimaryKeySelective(MarketTopic record);

    int updateByPrimaryKey(MarketTopic record);
}