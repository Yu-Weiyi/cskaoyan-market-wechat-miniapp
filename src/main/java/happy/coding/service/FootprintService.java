package happy.coding.service;

import happy.coding.bean.model.MarketFootprint;
import happy.coding.bean.vo.data.FootprintListData;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name FootprintService
 * @description Footprint service interface.
 * @since 2024-09-04 20:25
 */
public interface FootprintService {

    List<FootprintListData> list(int page, int limit);

    void insert(int goodsId);

    void delete(int footprintId);
}
