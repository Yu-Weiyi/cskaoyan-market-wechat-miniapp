package happy.coding.service.impl;

import happy.coding.bean.model.MarketUser;
import happy.coding.bean.model.MarketUserExample;
import happy.coding.bean.vo.data.AuthLoginData;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.AuthException;
import happy.coding.mapper.MarketUserMapper;
import happy.coding.service.AuthService;
import happy.coding.util.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name AuthServiceImpl
 * @description 认证鉴权服务实现。
 * @since 2024-09-02 19:11
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    MarketUserMapper marketUserMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * @param username java.lang.String
     * @param password java.lang.String
     * @return AuthLoginData
     * @name login
     * @description 登录。
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 19:14, CST
     */
    @Override
    public AuthLoginData login(String username, String password) {

        if (username == null || password == null) {
            throw new AuthException(ErrorCodeConstant.INVALID_PARAM);
        }

        // verify user existence
        MarketUserExample marketUserExample = new MarketUserExample();
        marketUserExample.createCriteria()
                .andUsernameEqualTo(username)
                .andDeletedEqualTo(false);
        List<MarketUser> marketUserList = marketUserMapper.selectByExample(marketUserExample);
        if (marketUserList.size() == 0) {
            throw new AuthException(ErrorCodeConstant.USER_NOT_FOUND);
        }
        MarketUser marketUser = marketUserList.get(0);

        // check user status
        Byte status = marketUser.getStatus();
        if (status != 0) {// TODO add more status
            throw new AuthException(ErrorCodeConstant.INVALID_USER_STATUS);
        }

        // verify password correctness
        String storedBcryptPassword = marketUser.getPassword();
        if (!BCrypt.checkpw(password, storedBcryptPassword)) {
            throw new AuthException(ErrorCodeConstant.PASSWORD_NOT_MATCH);
        }

        // gen JWT
        String id = marketUser.getId().toString();
        UserInfoContext.setUserId(Integer.valueOf(id));
        Date now = new Date();
        String jwt = jwtUtil.genToken(id, now);

        // redis store jwt
        redisTemplate.opsForValue().set(id, jwt, jwtUtil.getExpirationTime(), TimeUnit.SECONDS);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("nickName", marketUser.getNickname());
        userInfo.put("avatarUrl", marketUser.getAvatar());
        return new AuthLoginData(jwt, userInfo);
    }

    /**
     * @return void
     * @name logout
     * @description 登出。
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 21:02, CST
     */
    @Override
    public void logout() {

        Integer userId = UserInfoContext.getUserId();
        // redis delete jwt
        redisTemplate.delete(userId.toString());
    }
}
