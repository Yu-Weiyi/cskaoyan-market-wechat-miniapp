package happy.coding.configuration;

import happy.coding.bean.realm.JwtRedisRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.configuration
 * @name ShiroConfiguration
 * @description Shiro 配置。
 * @since 2024-09-01 14:18
 */
@Configuration
@Slf4j
public class ShiroConfiguration extends ShiroWebConfiguration {

    /**
     * @name realm
     * @description 返回 Shrio Realm。
     * @return Realm
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-01, Sun, 19:41, CST
     */
    @Bean
    public Realm realm() {

        return new JwtRedisRealm();
    }

    @Override
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {

        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        defaultShiroFilterChainDefinition.addPathDefinition("/auth/login/**", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/**", "authc");
        return defaultShiroFilterChainDefinition;
    }

    @Bean
    public EventBus eventBus() {

        return new DefaultEventBus();
    }
}
