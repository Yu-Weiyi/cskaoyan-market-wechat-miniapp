package happy.coding.service.impl;

import happy.coding.bean.model.MarketUser;
import happy.coding.bean.model.MarketUserExample;
import happy.coding.bean.vo.SmsSendResult;
import happy.coding.bean.vo.data.AuthLoginData;
import happy.coding.bean.vo.param.AuthRegisterParam;
import happy.coding.client.AliyunClient;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.AuthException;
import happy.coding.exception.ParamException;
import happy.coding.mapper.MarketUserMapper;
import happy.coding.service.AuthService;
import happy.coding.service.RedisService;
import happy.coding.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private MarketUserMapper marketUserMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AliyunClient aliyunClient;

    @Override
    public void regCaptcha(String mobile) {

        // avoid sending too often
        long ttl = redisService.ttlCaptcha(mobile);
        if (ttl > 240) {// 240 s = 4 min
            throw new AuthException(ErrorCodeConstant.CAPTCHA_SEND_TOO_OFFEN);
        }

        // send
        SmsSendResult smsSendResult = aliyunClient.sendValidationCode(mobile);
        String code = smsSendResult.getCode();

        redisService.setCaptcha(mobile, code, 5, TimeUnit.MINUTES);// 5 min
    }

    @Override
    public AuthLoginData register(AuthRegisterParam authRegisterParam) {

        MarketUserExample marketUserExample = new MarketUserExample();
        marketUserExample.createCriteria()
                .andUsernameEqualTo(authRegisterParam.getUsername());
        marketUserExample.or()
                .andMobileEqualTo(authRegisterParam.getMobile());
        List<MarketUser> marketUserList = marketUserMapper.selectByExample(marketUserExample);
        if (marketUserList != null && marketUserList.size() > 0) {
            throw new AuthException(ErrorCodeConstant.QUERY_FAILED);
        }

        if (!authRegisterParam.getCode().equals(redisService.getCaptcha(authRegisterParam.getMobile()))) {
            throw new AuthException(ErrorCodeConstant.QUERY_FAILED);
        }
        redisService.delCaptcha(authRegisterParam.getMobile());

        Date now = new Date();
        MarketUser marketUser = new MarketUser();
        marketUser.setUsername(authRegisterParam.getUsername());
        marketUser.setPassword(BCrypt.hashpw(authRegisterParam.getPassword(), BCrypt.gensalt()));
        marketUser.setGender((byte) 0);
        marketUser.setMobile(authRegisterParam.getMobile());
        marketUser.setAddTime(now);
        marketUser.setUpdateTime(now);
        marketUser.setUserLevel((byte) 0);
        marketUser.setNickname(authRegisterParam.getUsername());
        marketUser.setStatus((byte) 0);
        marketUser.setDeleted(false);
        marketUserMapper.insertSelective(marketUser);

        AuthLoginData login = login(authRegisterParam.getUsername(), authRegisterParam.getPassword());
        return login;
    }

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
            throw new ParamException(ErrorCodeConstant.INVALID_PARAM);
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
        redisService.setJwt(id, jwt, jwtUtil.getExpirationTime(), TimeUnit.SECONDS);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("nickName", marketUser.getNickname());
        userInfo.put("avatarUrl", marketUser.getAvatar());
        return new AuthLoginData(jwt, userInfo);
    }

    @Override
    public void authenticate(String jwtToken) {

        log.debug("校验 JWT(" + jwtToken + ")");
        Integer userId = Integer.valueOf(jwtUtil.extractId(jwtToken));

        String storedJwtToken = redisService.getJwt(userId.toString());
        if (storedJwtToken == null) {
            throw new AuthException(ErrorCodeConstant.TOKEN_EXPIRED);
        }
        if (!storedJwtToken.equals(jwtToken)) {
            throw new AuthException(ErrorCodeConstant.INVALID_TOKEN);
        }

        // auth pass
        UserInfoContext.setUserId(userId);
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
        redisService.delJwt(userId.toString());
    }
}
