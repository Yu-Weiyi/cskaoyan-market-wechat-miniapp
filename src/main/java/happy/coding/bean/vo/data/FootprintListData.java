package happy.coding.bean.vo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.data
 * @name FootprintListData
 * @description Footprint list data.
 * @since 2024-09-04 20:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FootprintListData {

    private Integer id;
    private String name;
    private Integer goodsId;
    private String picUrl;
    private String brief;
    private BigDecimal retailPrice;
    private Date addTime;
}
