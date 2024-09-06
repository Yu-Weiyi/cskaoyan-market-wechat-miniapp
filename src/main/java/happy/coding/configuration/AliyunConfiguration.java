package happy.coding.configuration;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import darabonba.core.client.ClientOverrideConfiguration;
import happy.coding.client.AliyunClient;
import happy.coding.properties.AliyunProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(AliyunProperties.class)
//@AllArgsConstructor
public class AliyunConfiguration {

    public AliyunConfiguration(AliyunProperties aliyunProperties) {
        this.aliyunProperties = aliyunProperties;
    }

    AliyunProperties aliyunProperties; // 构造器注入

    @Bean
    @Scope("prototype")
    public OSS ossClient() {
        String accessKeyId = aliyunProperties.getAccessKeyId();
        String accessKeySecret = aliyunProperties.getAccessKeySecret();
        String endpoint = aliyunProperties.getOss().getEndPoint();
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }

    @Bean
    @Scope("prototype")
    public AsyncClient asyncClient() {
        String accessKeyId = aliyunProperties.getAccessKeyId();
        String accessKeySecret = aliyunProperties.getAccessKeySecret();

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                .build());

        AsyncClient client = AsyncClient.builder()
                //.region("cn-qingdao") // Region ID
                .region(aliyunProperties.getSms().getRegion())
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
        return client;
    }

    @Bean
    //public aliyunClient aliyunClient(OSS oss,AsyncClient asyncClient) {
    public AliyunClient aliyunClient() {
        AliyunClient aliyunClient = new AliyunClient();
        //aliyunClient.setOss(oss);
        //aliyunClient.setAsyncClient(asyncClient);

        String bucket = aliyunProperties.getOss().getBucket();
        String urlPrefix = aliyunProperties.getOss().getUrlPrefix();
        aliyunClient.setBucketName(bucket);
        aliyunClient.setUrlPrefix(urlPrefix);

        aliyunClient.setSignName(aliyunProperties.getSms().getSignName());
        aliyunClient.setTemplateCode(aliyunProperties.getSms().getTemplateCode());

        return aliyunClient;
    }
}
