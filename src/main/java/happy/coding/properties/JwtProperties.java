package happy.coding.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding
 * @name JwtProperties
 * @description JWT 配置属性。
 * @since 2024-09-01 22:34
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String cookieKey;

    private String algorithm;
    private String type;

    private String subject;
    private String audience;
    private String issuer;
    private int expirationTime;
    private String customIdKey;

    private String secret;
}
