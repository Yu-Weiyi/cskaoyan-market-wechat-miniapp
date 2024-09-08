package happy.coding.configuration;

import happy.coding.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.configuration
 * @name RedisConfiguration
 * @description Redis 配置。
 * @since 2024-09-02 14:10
 */
@Configuration
@Slf4j
public class RedisConfiguration {

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        StringRedisSerializer serializer = new StringRedisSerializer();
//        template.setKeySerializer(serializer);
//        template.setValueSerializer(serializer);
//        template.setHashKeySerializer(serializer);
//        template.setHashValueSerializer(serializer);
//        log.info("Redis 客户端 配置完成。");
//        return template;
//    }

    @Autowired
    private RedisProperties redisProperties;

    private RedisConnectionFactory redisConnectionFactory(RedisProperties.RedisConfig redisConfig) {

        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisConfig.getHost());
        factory.setPort(redisConfig.getPort());
        factory.setPassword(redisConfig.getPassword());
        factory.setDatabase(redisConfig.getDatabase());
        return factory;
    }

    @Bean(name = "redisConnectionFactory0LoginJwt")
    @Primary
    public RedisConnectionFactory redisConnectionFactory0LoginJwt() {

        RedisProperties.RedisConfig db0LoginJwt = redisProperties.getDb0();
        RedisConnectionFactory factory = redisConnectionFactory(db0LoginJwt);
        log.info("Redis 数据库[0] JedisConnectionFactory 配置完成。");
        return factory;
    }

    @Bean(name = "redisConnectionFactory1RegisterCaptcha")
    public RedisConnectionFactory redisConnectionFactory1() {

        RedisProperties.RedisConfig db1RegisterCaptcha = redisProperties.getDb1();
        RedisConnectionFactory factory = redisConnectionFactory(db1RegisterCaptcha);
        log.info("Redis 数据库[1] JedisConnectionFactory 配置完成。");
        return factory;
    }

    private <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory, Class<T> clazz) {

        RedisTemplate<String, T> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
        return template;
    }

    @Bean(name = "redisTemplate0LoginJwt")
    public RedisTemplate<String, String> redisTemplate0LoginJwt(@Qualifier("redisConnectionFactory0LoginJwt") RedisConnectionFactory redisConnectionFactory0LoginJwt) {

        RedisTemplate<String, String> stringStringRedisTemplate = redisTemplate(redisConnectionFactory0LoginJwt, String.class);
        log.info("Redis 数据库[0] RedisTemplate 配置完成。");
        return stringStringRedisTemplate;
    }

    @Bean(name = "redisTemplate1RegisterCaptcha")
    public RedisTemplate<String, String> redisTemplate1RegisterCaptcha(@Qualifier("redisConnectionFactory1RegisterCaptcha") RedisConnectionFactory redisConnectionFactory1RegisterCaptcha) {

        RedisTemplate<String, String> stringStringRedisTemplate = redisTemplate(redisConnectionFactory1RegisterCaptcha, String.class);
        log.info("Redis 数据库[1] RedisTemplate 配置完成。");
        return stringStringRedisTemplate;
    }
}
