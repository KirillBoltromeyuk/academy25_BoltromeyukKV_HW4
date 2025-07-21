package by.it_academy.jd2.controller.filter;


import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserRole;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/ui/admin/*", "/api/admin/*"})
public class AdminSecurityFilter implements Filter {
    private final IUserService userService = new UserService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();
        if ((session != null) && (session.getAttribute("user") != null)) {
            User authUser = userService.getByLogin((String) session.getAttribute("user"));
            if (authUser.getRole()== UserRole.ADMIN) {
                filterChain.doFilter(request, response);
            }
            else {
                response.sendRedirect(contextPath + "/api/login");
            }
        } else {
            response.sendRedirect(contextPath + "/api/login");
        }
    }
}
