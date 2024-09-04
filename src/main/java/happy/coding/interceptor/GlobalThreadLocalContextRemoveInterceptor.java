package happy.coding.interceptor;

import happy.coding.context.PageInfoContext;
import happy.coding.context.UserInfoContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.interceptor
 * @name GlobalThreadLocalContextRemoveInterceptor
 * @description Global thread local context remove interceptor.
 * @since 2024-09-04 22:25
 */
@Component
@Slf4j
public class GlobalThreadLocalContextRemoveInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        // avoid memory leak
        UserInfoContext.removeUserId();
        PageInfoContext.removePageInfo();
    }
}
