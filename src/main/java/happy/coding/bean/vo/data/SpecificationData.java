package happy.coding.bean.vo.data;

import happy.coding.bean.model.MarketGoodsSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name SpecificationData
 * @description
 * @since 2024-09-04 19:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationData {

    private String name;
    private List<MarketGoodsSpecification> valueList;
}
