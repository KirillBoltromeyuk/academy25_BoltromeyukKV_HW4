package by.it_academy.jd2.controller;
import by.it_academy.jd2.core.ContextFactory;
import by.it_academy.jd2.core.dto.User;
import by.it_academy.jd2.core.dto.UserReg;
import by.it_academy.jd2.core.dto.UserRole;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet(urlPatterns = "/api/user")
public class RegistrationServlet extends HttpServlet {
    private final IUserService userService = ContextFactory.getBean(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/views/ui/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            userService.add(new UserReg.Builder()
                    .setLogin(req.getParameter("login").toLowerCase())
                    .setPassword(req.getParameter("password"))
                    .setName(req.getParameter("name"))
                    .setYear(req.getParameter("year"))
                    .setMonth(req.getParameter("month"))
                    .setDay(req.getParameter("day"))
                    .build());
        }catch (ValidatorException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/views/ui/signUp.jsp").forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() +"/api/login");
    }
}
