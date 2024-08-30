package happy.coding.mapper;

import happy.coding.bean.model.MarketComment;
import happy.coding.bean.model.MarketCommentExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MarketCommentMapper {
    long countByExample(MarketCommentExample example);

    int deleteByExample(MarketCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketComment record);

    int insertSelective(MarketComment record);

    List<MarketComment> selectByExample(MarketCommentExample example);

    MarketComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketComment record, @Param("example") MarketCommentExample example);

    int updateByExample(@Param("record") MarketComment record, @Param("example") MarketCommentExample example);

    int updateByPrimaryKeySelective(MarketComment record);

    int updateByPrimaryKey(MarketComment record);
}