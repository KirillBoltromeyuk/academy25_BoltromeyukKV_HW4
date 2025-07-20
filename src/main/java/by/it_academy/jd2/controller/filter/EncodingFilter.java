package by.it_academy.jd2.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EncodingFilter extends HttpFilter {

    private final static String AUTH_HEADER = "X-SUPERADMIN";
    private final static String ADMIN_PASSWORD = "123";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);

        response.setContentType("text/html; charset=utf-8");
    }
}