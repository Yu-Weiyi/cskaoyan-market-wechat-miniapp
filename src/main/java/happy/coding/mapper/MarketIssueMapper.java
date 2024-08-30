package happy.coding.mapper;

import happy.coding.bean.model.MarketIssue;
import happy.coding.bean.model.MarketIssueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketIssueMapper {
    long countByExample(MarketIssueExample example);

    int deleteByExample(MarketIssueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketIssue record);

    int insertSelective(MarketIssue record);

    List<MarketIssue> selectByExample(MarketIssueExample example);

    MarketIssue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketIssue record, @Param("example") MarketIssueExample example);

    int updateByExample(@Param("record") MarketIssue record, @Param("example") MarketIssueExample example);

    int updateByPrimaryKeySelective(MarketIssue record);

    int updateByPrimaryKey(MarketIssue record);
}