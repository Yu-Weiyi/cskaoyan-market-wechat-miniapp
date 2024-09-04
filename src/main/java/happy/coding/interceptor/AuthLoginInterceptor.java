package happy.coding.interceptor;

import happy.coding.bean.model.MarketUser;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketUserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.interceptor
 * @name AuthLoginInterceptor
 * @description Auth login interceptor.
 * @since 2024-09-02 22:10
 */
@Component
@Slf4j
public class AuthLoginInterceptor implements HandlerInterceptor {

    @Autowired
    MarketUserMapper marketUserMapper;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Date now = new Date();
        String ip = request.getRemoteHost();

        // update login time & ip
        MarketUser marketUser = new MarketUser();
        marketUser.setId(UserInfoContext.getUserId());
        marketUser.setLastLoginTime(now);
        marketUser.setLastLoginIp(ip);
        marketUserMapper.updateByPrimaryKeySelective(marketUser);
    }
}
