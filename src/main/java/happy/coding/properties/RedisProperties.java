package happy.coding.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.properties
 * @name RedisProperties
 * @description Redis properties.
 * @since 2024-09-09 03:01
 */
@Data
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {

    private RedisConfig db0;
    private RedisConfig db1;

    @Data
    public static class RedisConfig {
        private String host;
        private int port;
        private String password;
        private int database;
        private int timeout;
    }
}