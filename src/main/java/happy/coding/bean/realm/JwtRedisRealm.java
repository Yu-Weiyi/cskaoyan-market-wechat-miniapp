package happy.coding.bean.realm;

import happy.coding.bean.model.MarketUser;
import happy.coding.bean.model.MarketUserExample;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.AuthException;
import happy.coding.mapper.MarketUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.realm
 * @name JwtRedisRealm
 * @description Shiro 认证域对象，使用 JWT 认证，Redis 作为存储。
 * @since 2024-09-01 19:50
 */
@Component
@Slf4j
public class JwtRedisRealm extends AuthorizingRealm {

    @Autowired
    MarketUserMapper marketUserMapper;

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {

        return authenticationToken instanceof UsernamePasswordToken;
    }

    /**
     * @name doGetAuthenticationInfo
     * @description 认证。
     * @param authenticationToken org.apache.shiro.authc.AuthenticationToken
     * @return AuthenticationInfo
     * @throws AuthException
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 15:37, CST
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthException {

        log.info("doGetAuthenticationInfo");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

        // check params existence
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
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

        return new SimpleAuthenticationInfo(marketUser.getId(), storedBcryptPassword, getName());
    }

    /**
     * @name doGetAuthorizationInfo
     * @description 授权。
     * @param principalCollection org.apache.shiro.subject.PrincipalCollection
     * @return AuthorizationInfo
     * @throws AuthException
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 15:36, CST
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Integer id = (Integer) principalCollection.getPrimaryPrincipal();

        // set role by user level
        MarketUser marketUser = marketUserMapper.selectByPrimaryKey(id);
        if (marketUser == null) {
            throw new AuthException(ErrorCodeConstant.USER_NOT_FOUND);
        }
        Byte level = marketUser.getUserLevel();
        Set<String> roleSet = new HashSet<>();
        switch (level) {// TODO magic number
            case 2:
                roleSet.add("user-level-2");
            case 1:
                roleSet.add("user-level-1");
            case 0:
                roleSet.add("user-level-0");
                break;
            default:
                throw new AuthException(ErrorCodeConstant.INVALID_USER_LEVEL);
        }
        return new SimpleAuthorizationInfo(roleSet);
    }
}
