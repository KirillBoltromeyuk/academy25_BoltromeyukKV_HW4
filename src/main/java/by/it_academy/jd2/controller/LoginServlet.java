package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("template/loginForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String login = req.getParameter("login");
       String password = req.getParameter("password");
       if (login == null || password == null ) {
           resp.sendError(400, "логин и пароль не должны быть пустыми");
       }
       if(userService.authoriseUser(login, password)) {
           HttpSession session = req.getSession();
           session.setAttribute("user", login);
           resp.sendRedirect("/");
       }else resp.sendError(400,"неверное имя пользователя или пароль");
    }
}
