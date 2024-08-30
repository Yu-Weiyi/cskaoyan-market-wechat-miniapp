package happy.coding.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wx/goods/*")
public class GoodsServlet extends CommonServlet{
    public void count(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(100);
    }
}
