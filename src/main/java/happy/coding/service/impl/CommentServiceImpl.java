package happy.coding.service.impl;

import happy.coding.bean.model.MarketComment;
import happy.coding.bean.model.MarketCommentExample;
import happy.coding.mapper.MarketCommentMapper;
import happy.coding.mapper.MarketCouponMapper;
import happy.coding.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<MarketComment> listByGoodsId(int goodsId) {

        MarketCommentExample marketCommentExample = new MarketCommentExample();
        marketCommentExample.createCriteria()
                .andValueIdEqualTo(goodsId)
                .andTypeEqualTo((byte) 0)
                .andDeletedEqualTo(false);
        marketCommentExample.setOrderByClause("add_time DESC");
        List<MarketComment> marketCommentList = marketCommentMapper.selectByExample(marketCommentExample);
        return marketCommentList;
    }
}
