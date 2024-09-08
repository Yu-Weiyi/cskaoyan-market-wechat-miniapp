package happy.coding.service.impl;

import happy.coding.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name RedisServiceImpl
 * @description Redis service implement.
 * @since 2024-09-09 02:10
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate0LoginJwt;
    @Autowired
    private RedisTemplate<String, String> redisTemplate1RegisterCaptcha;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory0LoginJwt;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory1RegisterCaptcha;

    @Override
    public void setJwt(String userId, String token, long expire, TimeUnit unit) {

        redisTemplate0LoginJwt.opsForValue().set(userId, token, expire, unit);
    }

    @Override
    public String getJwt(String userId) {

        return redisTemplate0LoginJwt.opsForValue().get(userId);
    }

    @Override
    public boolean delJwt(String userId) {

        return redisTemplate0LoginJwt.delete(userId);
    }

    @Override
    public void setCaptcha(String mobile, String captcha, long expire, TimeUnit unit) {

        redisTemplate1RegisterCaptcha.opsForValue().set(mobile, captcha, expire, unit);
    }

    @Override
    public long ttlCaptcha(String mobile) {

        return redisTemplate1RegisterCaptcha.getExpire(mobile);
    }

    @Override
    public String getCaptcha(String mobile) {

        return redisTemplate1RegisterCaptcha.opsForValue().get(mobile);
    }

    @Override
    public boolean delCaptcha(String mobile) {

        return redisTemplate1RegisterCaptcha.delete(mobile);
    }
}
