package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketComment;
import happy.coding.bean.model.MarketCommentExample;
import happy.coding.mapper.MarketCommentMapper;
import happy.coding.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name CommentServiceImpl
 * @description Comment service implement.
 * @since 2024-09-04 15:09
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MarketCommentMapper marketCommentMapper;

    @Override
    public List<MarketComment> list(int valueId, byte type, int page, int limit) {

        MarketCommentExample marketCommentExample = new MarketCommentExample();
        marketCommentExample.createCriteria()
                .andValueIdEqualTo(valueId)
                .andTypeEqualTo(type)
                .andDeletedEqualTo(false);
        marketCommentExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketComment> marketCommentList = marketCommentMapper.selectByExample(marketCommentExample);
        return marketCommentList;
    }

    @Override
    public Map<String, Long> count(int valueId, byte type) {

        // for allCount
        MarketCommentExample marketCommentExample0 = new MarketCommentExample();
        marketCommentExample0.createCriteria()
                .andValueIdEqualTo(valueId)
                .andTypeEqualTo(type)
                .andDeletedEqualTo(false);
        long allCount = marketCommentMapper.countByExample(marketCommentExample0);

        // for hasPicCount
        MarketCommentExample marketCommentExample1 = new MarketCommentExample();
        marketCommentExample1.createCriteria()
                .andValueIdEqualTo(valueId)
                .andTypeEqualTo(type)
                .andPicUrlsNotEqualTo("[]")
                .andDeletedEqualTo(false);
        long hasPicCount = marketCommentMapper.countByExample(marketCommentExample1);

        return Map.of("allCount", allCount, "hasPicCount", hasPicCount);
    }
}
