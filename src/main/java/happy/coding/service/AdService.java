package happy.coding.service;

import happy.coding.bean.model.MarketAd;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name AdService
 * @description Ad service interface.
 * @since 2024-09-03 19:59
 */
public interface AdService {

    List<MarketAd> listAll();
}
