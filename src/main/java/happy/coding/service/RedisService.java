package happy.coding.service;

import java.util.concurrent.TimeUnit;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name RedisService
 * @description Redis service interface.
 * @since 2024-09-09 02:10
 */
public interface RedisService {

    void setJwt(String userId, String token, long expire, TimeUnit unit);

    String getJwt(String userId);

    boolean delJwt(String userId);

    void setCaptcha(String mobile, String captcha, long expire, TimeUnit unit);

    long ttlCaptcha(String mobile);

    String getCaptcha(String mobile);

    boolean delCaptcha(String mobile);
}
