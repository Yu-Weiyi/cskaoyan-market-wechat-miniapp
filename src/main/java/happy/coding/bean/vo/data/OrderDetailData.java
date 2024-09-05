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
 * @name OrderDetailData
 * @description Order detail data.
 * @since 2024-09-05 18:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailData {

    private Integer id;
    private String orderSn;
    private String message;
    private String mobile;
    private Date addTime;
    private String address;
    private String consignee;
    private String orderStatusText;
    private BigDecimal actualPrice;
    private BigDecimal couponPrice;
    private BigDecimal freightPrice;
    private BigDecimal goodsPrice;
    private Short aftersaleStatus;
    private HandleOptionData handleOption;
}

