package happy.coding.interceptor;

import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.AuthException;
import happy.coding.service.AuthService;
import happy.coding.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.interceptor
 * @name FullAuthJwtTokenInterceptor
 * @description JWT token interceptor.
 * @since 2024-09-02 21:05
 */
@Component
@Slf4j
public class FullAuthJwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        try {
            String token = request.getHeader(jwtUtil.getCookieKey());

            authService.authenticate(token);

            return true;
        } catch (AuthException ex) {
//            response.setStatus(401);
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println("{\"errno\":" + ErrorCodeConstant.PLEASE_LOGIN.getErrno() + ",\"errmsg\":\"" + ex.getErrmsg() + "\"}");
            return false;
        } catch (Exception ex) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println("{\"errno\":" + ErrorCodeConstant.PLEASE_LOGIN.getErrno() + ",\"errmsg\":\"" + ErrorCodeConstant.PLEASE_LOGIN.getErrmsg() + "\"}");
            return false;
        }
    }
}
