package cn.yellowgg.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author:黄广
 * @Description:自定义servlet类，使用反射执行url的方法
 * @Date: Created in 19-3-18 下午4:27
 */
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 采用这种写法，url传过来一个method参数，然后它的值就是servlet里面的方法名
     * <p>
     * 传过来什么参数就执行什么方法，这样就可以实现一个servlet里面执行多个方法，而不用开发很多servlet了
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //1.获取执行的方法名称
            String mName = req.getParameter("method");

            //1.1判断方法名是否为空，若为空，执行默认方法告知url有误
            if (mName == null || mName.trim().length() == 0) {
                mName = "index";
            }

            //2.获取方法对象
            Method method = this.getClass().getMethod(mName, HttpServletRequest.class, HttpServletResponse.class);

            //3.让方法执行，接收返回值，一般返回值就是一个jsp的路径，然后在下面统一转发
            String path = (String) method.invoke(this, req, resp);

            //4.判断返回值是否为空，若不为空统一处理请求转发
            if (path != null) {
                req.getRequestDispatcher(path).forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("url找不到，请重试！");
        return null;
    }
}
