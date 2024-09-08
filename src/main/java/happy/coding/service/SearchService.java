package happy.coding.service;

import happy.coding.bean.vo.data.SearchIndexData;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name SearchService
 * @description Search service interface.
 * @since 2024-09-08 23:06
 */
public interface SearchService{

    SearchIndexData index();

    List<String> helper(String keyword);

    void clearhistory();

    void addHistory(String keyword);
}
