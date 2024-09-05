package happy.coding.service;

import happy.coding.bean.vo.data.OrderListData;

import java.util.List;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name OrderService
 * @description Order service interface.
 * @since 2024-09-04 10:10
 */
public interface OrderService {

    long countByStatus(short status);

    long countUnpaid();

    long countUnShip();

    long countUnrecv();

    long countUnComment();

    List<OrderListData> list(int showType, int page, int limit);

    Map<String ,Object> detail(Integer orderId);

    void cancel(Integer orderId);

    void refund(Integer orderId);

    void confirm(Integer orderId);

    void delete(Integer orderId);
}