package happy.coding.service;

import happy.coding.bean.model.MarketAddress;
import happy.coding.bean.vo.param.AddressSaveParam;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name AddressService
 * @description Address service interface.
 * @since 2024-09-05 23:46
 */
public interface AddressService {

    List<MarketAddress> list();

    int save(AddressSaveParam addressSaveParam);

    MarketAddress detail(Integer addressId);

    void delete(Integer addressId);

    String getFullAddress(Integer addressId);

    String getFullAddress(MarketAddress marketAddress);
}
