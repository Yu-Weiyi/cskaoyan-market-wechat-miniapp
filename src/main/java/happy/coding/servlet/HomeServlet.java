package happy.coding.servlet;


import happy.coding.service.HomeService;
import org.springframework.context.ApplicationContext;
import happy.coding.aspect.BaseTransaction;
import happy.coding.bean.vo.BaseRespVo;
import happy.coding.bean.vo.data.HomeIndexData;
import happy.coding.util.JacksonUtil;
import happy.coding.util.WordMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wx/home/*")
public class HomeServlet extends CommonServlet{
    @BaseTransaction
    public void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("执行了执行了");
        ApplicationContext applicationContext=(ApplicationContext) getServletContext().getAttribute(WordMap.APPLICATION_CONTEXT);
        HomeService homeService = applicationContext.getBean(HomeService.class);
        HomeIndexData homeIndexData=homeService.index(null);

        resp.getWriter().println(JacksonUtil.write(BaseRespVo.success(homeIndexData)));
    }

}
