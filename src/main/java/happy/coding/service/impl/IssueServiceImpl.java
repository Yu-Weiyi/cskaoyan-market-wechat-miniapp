package happy.coding.service.impl;

import happy.coding.bean.model.MarketIssue;
import happy.coding.bean.model.MarketIssueExample;
import happy.coding.mapper.MarketIssueMapper;
import happy.coding.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name IssueServiceImpl
 * @description Issue service implement.
 * @since 2024-09-04 15:39
 */
@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private MarketIssueMapper marketIssueMapper;

    @Override
    public List<MarketIssue> listAll() {

        MarketIssueExample marketIssueExample = new MarketIssueExample();
        marketIssueExample.createCriteria()
                .andDeletedEqualTo(false);
        List<MarketIssue> marketIssueList = marketIssueMapper.selectByExample(marketIssueExample);
        return marketIssueList;
    }
}
