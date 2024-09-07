package happy.coding.service;

import java.math.BigDecimal;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name FreightService
 * @description Freight service interface.
 * @since 2024-09-07 09:44
 */
public interface FreightService {

    BigDecimal calc(BigDecimal goodsTotalPrice);
}
