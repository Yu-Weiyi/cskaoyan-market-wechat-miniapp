package happy.coding.bean.vo.data;

import happy.coding.bean.model.MarketGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name HomeIndexFloorGoodsListData
 * @description
 * @since 2024-09-03 23:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeIndexFloorGoodsListData {

    private Integer id;
    private String name;
    private List<MarketGoods> goodsList;
}
