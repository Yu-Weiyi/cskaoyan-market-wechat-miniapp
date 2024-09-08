package happy.coding.bean.vo.data;

import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.model.MarketTopic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name TopicDetailData
 * @description Topic detail data.
 * @since 2024-09-08 15:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDetailData {

    private MarketTopic topic;
    private List<MarketGoods> goods;
}
