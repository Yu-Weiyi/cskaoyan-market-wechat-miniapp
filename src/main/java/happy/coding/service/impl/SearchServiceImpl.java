package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketKeyword;
import happy.coding.bean.model.MarketKeywordExample;
import happy.coding.bean.model.MarketSearchHistory;
import happy.coding.bean.model.MarketSearchHistoryExample;
import happy.coding.bean.vo.data.SearchIndexData;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketKeywordMapper;
import happy.coding.mapper.MarketSearchHistoryMapper;
import happy.coding.service.GoodsService;
import happy.coding.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name SearchServiceImpl
 * @description Search service implement.
 * @since 2024-09-08 23:06
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MarketKeywordMapper marketKeywordMapper;
    @Autowired
    private MarketSearchHistoryMapper marketSearchHistoryMapper;
    @Autowired
    private GoodsService goodsService;

    @Override
    public SearchIndexData index() {

        SearchIndexData index = new SearchIndexData();

        index.setDefaultKeyword(selectDefault());
        index.setHistoryKeywordList(selectHistory().stream()
                .map(MarketSearchHistory::getKeyword)
                .map(item -> Map.of("keyword", item))
                .toList());
        index.setHotKeywordList(selectHot());

        return index;
    }

    @Override
    public List<String> helper(String keyword) {

        List<String> helper = goodsService.helper(keyword);
        return helper;
    }

    @Override
    public void clearhistory() {

        Date now = new Date();

        MarketSearchHistory marketSearchHistory = new MarketSearchHistory();
        marketSearchHistory.setUpdateTime(now);
        marketSearchHistory.setDeleted(true);

        MarketSearchHistoryExample marketSearchHistoryExample = new MarketSearchHistoryExample();
        marketSearchHistoryExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andDeletedEqualTo(false);

        marketSearchHistoryMapper.updateByExampleSelective(marketSearchHistory, marketSearchHistoryExample);
    }

    @Override
    public void addHistory(String keyword) {

        Date now = new Date();

        MarketSearchHistory marketSearchHistory = new MarketSearchHistory();
        marketSearchHistory.setKeyword(keyword);
        marketSearchHistory.setUserId(UserInfoContext.getUserId());
        marketSearchHistory.setUpdateTime(now);
        marketSearchHistory.setAddTime(now);
        marketSearchHistory.setDeleted(false);
        marketSearchHistoryMapper.insert(marketSearchHistory);
    }

    private MarketKeyword selectDefault() {

        MarketKeywordExample defaultExample = new MarketKeywordExample();
        defaultExample.createCriteria()
                .andIsDefaultEqualTo(true)
                .andDeletedEqualTo(false);
        defaultExample.setOrderByClause("sort_order");
        PageHelper.startPage(1, 1);
        List<MarketKeyword> marketKeywordList = marketKeywordMapper.selectByExample(defaultExample);
        if (marketKeywordList == null || marketKeywordList.isEmpty()) {
            return null;
        } else {
            return marketKeywordList.get(0);
        }
    }

    private List<MarketSearchHistory> selectHistory() {

        MarketSearchHistoryExample historyExample = new MarketSearchHistoryExample();
        historyExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andDeletedEqualTo(false);
        historyExample.setOrderByClause("add_time desc");
        List<MarketSearchHistory> marketSearchHistoryList = marketSearchHistoryMapper.selectByExample(historyExample);
        return marketSearchHistoryList;
    }

    private List<MarketKeyword> selectHot() {

        MarketKeywordExample hotExample = new MarketKeywordExample();
        hotExample.createCriteria()
                .andIsHotEqualTo(true)
                .andDeletedEqualTo(false);
        hotExample.setOrderByClause("sort_order");
        List<MarketKeyword> marketKeywordList = marketKeywordMapper.selectByExample(hotExample);
        return marketKeywordList;
    }
}
