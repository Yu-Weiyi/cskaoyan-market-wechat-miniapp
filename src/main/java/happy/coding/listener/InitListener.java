package happy.coding.listener;

import happy.coding.config.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import happy.coding.util.WordMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化的时候执行的方法,然后把spring的context放在servlet的context里面去处理
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(SpringConfiguration.class);
        System.out.println("运行了");
        servletContextEvent.getServletContext().setAttribute(WordMap.APPLICATION_CONTEXT,applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
