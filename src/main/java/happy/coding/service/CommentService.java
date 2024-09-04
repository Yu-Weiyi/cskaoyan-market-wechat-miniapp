package happy.coding.service;

import happy.coding.bean.model.MarketComment;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name CommentService
 * @description Comment service interface.
 * @since 2024-09-04 15:08
 */
public interface CommentService {

    List<MarketComment> listByGoodsId(int goodsId);
}
