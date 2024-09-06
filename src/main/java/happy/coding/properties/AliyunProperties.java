package happy.coding.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author stone
 * @date 2023/04/21 20:53
 */
@ConfigurationProperties("aliyun") // springboot config5的方式
@Data
public class AliyunProperties {
    String accessKeyId; // wd.aliyun.access-key-id
    String accessKeySecret; // wd.aliyun.access-key-secret
    Oss oss; // wd.aliyun.oss
    Sms sms; // wd.aliyun.sms

    @Data
    public static class Oss {
        String bucket; // wd.aliyun.oss.bucket
        String endPoint; // wd.aliyun.oss.end-point
        // https://${bucket}.${endpoint}/
        String urlPrefix; //wd.aliyun.oss.url-prefix
    }

    @Data
    public static class Sms {
        String signName; //wd.aliyun.sms.sign-name
        String templateCode; //wd.aliyun.sms.template-code
        String region;
    }
}
