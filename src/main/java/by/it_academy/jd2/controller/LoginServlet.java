package by.it_academy.jd2.controller;

import by.it_academy.jd2.core.ContextFactory;
import by.it_academy.jd2.service.AuthorisationService;
import by.it_academy.jd2.service.api.IAuthorisationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    private final IAuthorisationService authorisationService = ContextFactory.getBean(AuthorisationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/views/ui/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String login = req.getParameter("login");
       String password = req.getParameter("password");
       if (login == null || password == null ) {
           req.setAttribute("error", "логин и пароль не должны быть пустыми");
           req.getRequestDispatcher("/WEB-INF/jsp/views/ui/signIn.jsp").forward(req, resp);
       }
       if(authorisationService.authoriseUser(login, password)) {
           HttpSession session = req.getSession();
           session.setAttribute("user", login);
           resp.sendRedirect(req.getContextPath() +"/");
       }else {
           req.setAttribute("error", "неверное имя пользователя или пароль");
           req.getRequestDispatcher("/WEB-INF/jsp/views/ui/signIn.jsp").forward(req, resp);
       }
    }
}
