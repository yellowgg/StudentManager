package cn.yellowgg.servlet;

import cn.yellowgg.entity.User;
import cn.yellowgg.service.UserService;
import cn.yellowgg.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:黄广
 * @Description:用户模块
 * @Date: Created in 19-3-18 下午4:27
 */
public class UserServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    UserService us = new UserServiceImpl();

    /**
     * 登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String login(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        try {
            //1.获取用户名和密码
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            //2.调用service完成登录 返回值：user
            User currentUser = us.login(userName, password);

            //3.判断user 根据结果生成提示
            if (currentUser == null) {
                //用户名和密码不匹配
                request.setAttribute("msg", "抱歉，用户名和密码不匹配");
                return "index.jsp";
            }

            //登录成功 保存用户登录状态
            request.getSession().setAttribute("currentUser", currentUser);

            //跳转到主页
            response.sendRedirect("main.jsp");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "用户登录失败噢，请重新登录");
            return "index.jsp";
        }
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //清除当前session的所有相关信息
        //session.removeAttribute()适用于清空指定的属性
        request.getSession().invalidate();

        //重定向
        response.sendRedirect("index.jsp");
        return null;
    }
}
