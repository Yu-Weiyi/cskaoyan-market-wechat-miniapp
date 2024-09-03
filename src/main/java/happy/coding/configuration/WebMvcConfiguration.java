package happy.coding.configuration;

import happy.coding.interceptor.AuthLoginInterceptor;
import happy.coding.interceptor.JwtTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.configuration
 * @name WebMvcConfiguration
 * @description Web MVC configuration.
 * @since 2024-09-02 21:22
 */
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Autowired
    private AuthLoginInterceptor AuthLoginInterceptor;

    /**
     * @name addInterceptors
     * @description 注册拦截器。
     * @param interceptorRegistry org.springframework.web.servlet.config.annotation.InterceptorRegistry
     * @return void
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 21:29, CST
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

        interceptorRegistry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**")
                // 3rd party api
                .excludePathPatterns("/v3/api-docs/**")// api-docs
                .excludePathPatterns("/swagger-ui/**")// swagger-ui
                .excludePathPatterns("/doc.html")// knife4j
                .excludePathPatterns("/druid/**")// druid
                // health check api
                .excludePathPatterns("/health-check/**")
                // public api
                .excludePathPatterns("/home/index/**")
                // auth api
                .excludePathPatterns("/auth/register/**")
                .excludePathPatterns("/auth/login/**");
        interceptorRegistry.addInterceptor(AuthLoginInterceptor)
                        .addPathPatterns("/auth/login/**");
        log.info("拦截器 注册完成。");
    }

    /**
     * @name addResourceHandlers
     * @description 注册资源处理器。
     * @param resourceHandlerRegistry org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
     * @return void
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 21:30, CST
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

        resourceHandlerRegistry
                .addResourceHandler("doc.html")// knife4j
                .addResourceLocations("classpath:/META-INF/resources/");
        resourceHandlerRegistry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        log.info("资源处理器 注册完成。");
    }
}
