package happy.coding.service.impl;

import happy.coding.bean.vo.data.UserIndexData;
import happy.coding.service.OrderService;
import happy.coding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name UserServiceImpl
 * @description User service implement.
 * @since 2024-09-04 09:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    OrderService orderService;

    @Override
    public UserIndexData index() {

        UserIndexData order = new UserIndexData(
                orderService.countUnpaid(),
                orderService.countUnShip(),
                orderService.countUnrecv(),
                orderService.countUnComment()
        );
        return order;
    }
}
