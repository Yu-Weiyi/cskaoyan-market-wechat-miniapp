package happy.coding.bean.vo.data;

import happy.coding.bean.model.MarketKeyword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name SearchIndexData
 * @description Search index data.
 * @since 2024-09-08 23:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchIndexData {

    private MarketKeyword defaultKeyword;
    private List<Map<String, String>> historyKeywordList;
    private List<MarketKeyword> hotKeywordList;
}
