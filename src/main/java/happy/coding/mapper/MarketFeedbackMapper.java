package happy.coding.mapper;

import happy.coding.bean.model.MarketFeedback;
import happy.coding.bean.model.MarketFeedbackExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketFeedbackMapper {
    long countByExample(MarketFeedbackExample example);

    int deleteByExample(MarketFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketFeedback record);

    int insertSelective(MarketFeedback record);

    List<MarketFeedback> selectByExample(MarketFeedbackExample example);

    MarketFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketFeedback record, @Param("example") MarketFeedbackExample example);

    int updateByExample(@Param("record") MarketFeedback record, @Param("example") MarketFeedbackExample example);

    int updateByPrimaryKeySelective(MarketFeedback record);

    int updateByPrimaryKey(MarketFeedback record);
}