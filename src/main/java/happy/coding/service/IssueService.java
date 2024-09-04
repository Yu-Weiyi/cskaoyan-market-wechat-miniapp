package happy.coding.service;

import happy.coding.bean.model.MarketIssue;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name IssueService
 * @description Issue service interface.
 * @since 2024-09-04 15:38
 */
public interface IssueService {

    List<MarketIssue> listAll();
}
