package cn.yellowgg.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @Author:黄广
 * @Description:编码过滤器
 * @Date: Created in 19-3-21 下午5:05
 */
public class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置请求转码格式
        servletRequest.setCharacterEncoding("UTF-8");
        //设置response回传页面编码格式
        servletResponse.setContentType("text/html;charset=UTF-8");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
