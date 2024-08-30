package happy.coding.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }
    //get方法和post方法都要调用process这个方法
    protected void process(HttpServletRequest req, HttpServletResponse resp){
        //然后在这个地方要找到uri所对应的最后一个单词，然后用这个单词对应方法名，用反射
        //先找方法名
        String fnName=req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+1);
        //然后获取当前的class
        Class currentClass=this.getClass();
        try {
            Method useMethod = currentClass.getDeclaredMethod(fnName, HttpServletRequest.class, HttpServletResponse.class);
            if (useMethod!=null){
                useMethod.invoke(this,req,resp);
            }else {
                resp.getWriter().println("40404040404040440040404040404040404");
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
