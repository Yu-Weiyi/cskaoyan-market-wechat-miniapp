package happy.coding.bean.vo.data;

import happy.coding.bean.model.MarketOrderGoods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name OrderListData
 * @description Order list data.
 * @since 2024-09-05 16:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListData {

    private BigDecimal actualPrice;
    private Short aftersaleStatus;
    private List<MarketOrderGoods> goodsList;
    private HandleOptionData handleOption;
    private Integer id;
    private Boolean isGroupin;
    private String orderSn;
    private String orderStatusText;
}
