package happy.coding.interceptor;

import happy.coding.context.UserInfoContext;
import happy.coding.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.interceptor
 * @name JwtTokenInterceptor
 * @description JWT token interceptor.
 * @since 2024-09-02 21:05
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println(request.getRequestURI());
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        try {
            String token = request.getHeader(jwtUtil.getCookieKey());
            log.debug("校验 JWT(" + token + ")");
            Integer userId = Integer.valueOf(jwtUtil.extractId(token));
            UserInfoContext.setUserId(userId);
            return true;
        }
        catch (Exception ex) {
            response.setStatus(401);
            return false;
        }
    }
}
