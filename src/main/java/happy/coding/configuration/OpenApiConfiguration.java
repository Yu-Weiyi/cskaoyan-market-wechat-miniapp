package happy.coding.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.configuration
 * @name OpenApiConfiguration
 * @description OpenAPI 接口文档配置。
 * @since 2024-09-01 13:20
 */
@Configuration
@AutoConfigureBefore(SpringDocConfiguration.class)
@Slf4j
public class OpenApiConfiguration {

    /**
     * @name openApi
     * @description 配置 OpenAPI 接口文档具体信息。
     * @return OpenAPI
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-01, Sun, 13:27, CST
     */
    @Bean
    public OpenAPI openApi() {

        OpenAPI openApi = new OpenAPI()
                .info(
                        new Info()
                                .title("快乐写代码 项目二 OpenAPI接口文档")
                                .description("happy.coding project-two OpenAPI Document")
                                .version("version 1.0.0")
                                .contact(
                                        new Contact()
                                                .name("happy.coding 项目组")
                                )
                );
        log.info("配置 OpenAPI 接口文档具体信息。");
        return openApi;
    }
}
