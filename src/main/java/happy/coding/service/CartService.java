package happy.coding.service;

import happy.coding.bean.vo.param.CartAddParam;
import happy.coding.bean.vo.param.CartCheckedParam;
import happy.coding.bean.vo.param.CartUpdateParam;

import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name CartService
 * @description Cart service interface.
 * @since 2024-09-04 16:52
 */
public interface CartService {

    long goodscount();

    Map<String, Object> index();

    long add(CartAddParam cartAddParam);

    Map<String, Object> checked(CartCheckedParam cartCheckedParam);

    void update(CartUpdateParam cartUpdateParam);
}
