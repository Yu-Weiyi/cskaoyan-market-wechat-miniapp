package happy.coding.service;

import happy.coding.bean.vo.param.CollectAddordeleteParam;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name CollectService
 * @description Collect service interface.
 * @since 2024-09-04 16:16
 */
public interface CollectService {

    boolean hasCollectedGoods(int goodsId);

    boolean hasCollectedTopic(int topicId);

    List<? extends Object> list(byte type, int page, int limit);

    void addordelete(CollectAddordeleteParam collectAddordeleteParam);
}
