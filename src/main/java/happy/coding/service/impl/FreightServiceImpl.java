package happy.coding.service.impl;

import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.QueryException;
import happy.coding.service.FreightService;
import happy.coding.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name FreightServiceImpl
 * @description Freight service implement.
 * @since 2024-09-07 09:45
 */
@Service
public class FreightServiceImpl implements FreightService {

    @Autowired
    private SystemService systemService;

    @Override
    public BigDecimal calc(BigDecimal goodsTotalPrice) {

        Map<String, String> systemMap = systemService.mapAll();
        BigDecimal expressFreightMin = new BigDecimal(systemMap.get("market_express_freight_min"));
        BigDecimal freightPrice = new BigDecimal(systemMap.get("market_express_freight_value"));
        if (expressFreightMin == null || expressFreightMin.compareTo(BigDecimal.ZERO) < 0 ||
                freightPrice == null || freightPrice.compareTo(BigDecimal.ZERO) < 0
        ) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        return goodsTotalPrice.compareTo(expressFreightMin) >= 0 ? BigDecimal.ZERO : freightPrice;
    }
}
