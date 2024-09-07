package happy.coding.service.impl;

import happy.coding.bean.model.MarketFeedback;
import happy.coding.bean.model.MarketUser;
import happy.coding.bean.vo.param.FeedbackSubmitParam;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.mapper.MarketFeedbackMapper;
import happy.coding.mapper.MarketUserMapper;
import happy.coding.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name FeedbackServiceImpl
 * @description Feedback service implement.
 * @since 2024-09-08 01:32
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private MarketFeedbackMapper marketFeedbackMapper;
    @Autowired
    private MarketUserMapper marketUserMapper;

    @Override
    public void submit(FeedbackSubmitParam feedbackSubmitParam) {

        MarketUser marketUser = marketUserMapper.selectByPrimaryKey(UserInfoContext.getUserId());
        if (marketUser == null) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        Date now = new Date();

        MarketFeedback marketFeedback = new MarketFeedback();
        marketFeedback.setUserId(UserInfoContext.getUserId());
        marketFeedback.setUsername(marketUser.getUsername());
        marketFeedback.setMobile(feedbackSubmitParam.getMobile());
        marketFeedback.setFeedType(feedbackSubmitParam.getFeedType());
        marketFeedback.setContent(feedbackSubmitParam.getContent());
        marketFeedback.setStatus(1);// TODO What is that mean?
        marketFeedback.setHasPicture(feedbackSubmitParam.getHasPicture());
        marketFeedback.setPicUrls(feedbackSubmitParam.getPicUrls());
        marketFeedback.setAddTime(now);
        marketFeedback.setUpdateTime(now);
        marketFeedback.setDeleted(false);
        marketFeedbackMapper.insertSelective(marketFeedback);
    }
}
