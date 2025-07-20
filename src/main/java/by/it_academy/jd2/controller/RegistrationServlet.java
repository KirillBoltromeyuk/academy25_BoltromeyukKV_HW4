package by.it_academy.jd2.controller;
import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserRole;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("template/registrationForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = Integer.parseInt(req.getParameter("year"));
        int month = Integer.parseInt(req.getParameter("month"));
        int day = Integer.parseInt(req.getParameter("day"));
        if(year<1900 && year >LocalDateTime.now().getYear()){
            resp.sendError(400,"Неверный год");
        }
        if(month<1 || month>12){
            resp.sendError(400, "Неверный месяц");
        }
        if(day<1 || day>31){
            resp.sendError(400, "Неверный день");
        }
        userService.registerUser(new User.Builder()
                        .setLogin(req.getParameter("login"))
                        .setPassword(req.getParameter("password"))
                        .setName(req.getParameter("name"))
                        .setDateOfBirth(LocalDateTime.of(year, month,day,0,0,0))
                        .setDateOfCreate(LocalDateTime.now())
                        .setRole(UserRole.USER)
                        .build());
        resp.sendRedirect(req.getContextPath() +"/login");
    }
}
