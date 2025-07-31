package by.it_academy.jd2.controller;

import by.it_academy.jd2.core.ContextFactory;
import by.it_academy.jd2.core.dto.User;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    private final IUserService userService= ContextFactory.getBean(UserService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session!=null){
            String login = (String) session.getAttribute("user");
            if(login!=null){
                User authUser = userService.getByLogin(login);
                req.setAttribute("name", authUser.getName());
                req.setAttribute("role", authUser.getRole());
            }
        }

        req.getRequestDispatcher("/WEB-INF/jsp/views/ui/index.jsp").forward(req, resp);
    }
}
