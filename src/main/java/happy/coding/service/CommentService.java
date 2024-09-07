package happy.coding.service;

import happy.coding.bean.model.MarketComment;
import happy.coding.bean.vo.param.CommentPostParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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

    List<MarketComment> list(int valueId, byte type, int page, int limit);

    Map<String, Long> count(int valueId, byte type);

    MarketComment post(CommentPostParam commentPostParam);
}
