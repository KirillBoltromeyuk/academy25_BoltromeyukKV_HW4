package by.it_academy.jd2.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebFilter(urlPatterns = {"/api/message", "/api/chats"})
public class UserSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();
        if((session!=null)&&(session.getAttribute("user")!=null)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect(contextPath+"/api/login");
        }
    }
}
