package by.it_academy.jd2.controller.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

// /admin/* -> /admin/1 = true
// /admin/* -> /admin/1/2 = false
// /admin/** -> /admin/1 = true
// /admin/** -> /admin/1/2 = true
//@WebFilter("/admin/**")
public class SecureFilter extends HttpFilter {

    private final static String AUTH_HEADER = "X-SUPERADMIN";
    private final static String ADMIN_PASSWORD = "123";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(AUTH_HEADER);
//        if(header.equals(ADMIN_PASSWORD)){
//        if(header != null && header.equals(ADMIN_PASSWORD)){
//        if(Objects.equals(header, ADMIN_PASSWORD)){
        if(ADMIN_PASSWORD.equals(header)){
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("ТОЛЬКО ДЛЯ АДМИНА!!!!");
        }
    }
}
