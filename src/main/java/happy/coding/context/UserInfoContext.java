package happy.coding.context;

import org.springframework.stereotype.Component;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.context
 * @name UserInfoContext
 * @description User information context.
 * @since 2024-09-02 21:03
 */
public class UserInfoContext {

    private static ThreadLocal<Integer> userId = new ThreadLocal<>();

    public static void setUserId(Integer id) {

        userId.set(id);
    }

    public static Integer getUserId() {

        return userId.get();
    }

    public static void removeUserId() {

        userId.remove();
    }
}
