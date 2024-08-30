package happy.coding.mapper;

import happy.coding.bean.model.MarketNotice;
import happy.coding.bean.model.MarketNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MarketNoticeMapper {
    long countByExample(MarketNoticeExample example);

    int deleteByExample(MarketNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketNotice record);

    int insertSelective(MarketNotice record);

    List<MarketNotice> selectByExample(MarketNoticeExample example);

    MarketNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketNotice record, @Param("example") MarketNoticeExample example);

    int updateByExample(@Param("record") MarketNotice record, @Param("example") MarketNoticeExample example);

    int updateByPrimaryKeySelective(MarketNotice record);

    int updateByPrimaryKey(MarketNotice record);
}