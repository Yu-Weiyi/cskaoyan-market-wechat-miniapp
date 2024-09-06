package happy.coding.client;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import happy.coding.bean.vo.OssPutResult;
import happy.coding.bean.vo.SmsSendResult;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Data
public class AliyunClient implements ApplicationContextAware {

    String bucketName;

    String urlPrefix;
    String signName;
    String templateCode;


    public String send(String mobile, String code) {
        String signName = this.getSignName();
        String templateCode = this.getTemplateCode();
        //AsyncClient client = this.getAsyncClient();
        AsyncClient client = applicationContext.getBean(AsyncClient.class);

        String templateParam = "{\"code\":\"" + code + "\"}";
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(mobile)
                .signName(signName)
                .templateCode(templateCode)
                .templateParam(templateParam)
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = null;
        try {
            resp = response.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String jsonResult = new Gson().toJson(resp);
        System.out.println(jsonResult);
        client.close();
        return jsonResult;
    }
    public SmsSendResult sendValidationCode(String mobile) {
        // 生成验证码
        SecureRandom random = new SecureRandom();
        String code = String.valueOf(random.nextInt(899999) + 100000);
        String jsonResult = this.send(mobile, code);
        return new SmsSendResult(jsonResult,code);
    }

    public OssPutResult save(MultipartFile file)  {

        //OSS ossClient = this.getOss();
        OSS ossClient = applicationContext.getBean(OSS.class);
        String bucketName = this.getBucketName();
        String urlPrefix = this.getUrlPrefix();

        String fileName = file.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + suffix;
        //String url = urlPrefix + "/" + objectName;

        InputStream inputStream = null;
        PutObjectResult putObjectResult = null;
        try {
            inputStream = file.getInputStream();
            // 保存在哪个bucket、保存的文件名、输入流
            putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String accessUrl = urlPrefix + "/" + objectName;
        return new OssPutResult(putObjectResult, accessUrl, objectName);
    }
    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
