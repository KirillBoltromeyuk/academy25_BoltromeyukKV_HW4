package by.it_academy.jd2.controller;

import by.it_academy.jd2.core.ContextFactory;
import by.it_academy.jd2.service.MessageService;
import by.it_academy.jd2.service.UserService;
import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/chats")
public class ChatsServlet extends HttpServlet {
    private final IMessageService messageService = ContextFactory.getBean(MessageService.class);
    private final IUserService userService = ContextFactory.getBean(UserService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("user");
        String name = userService.getByLogin(login).getName();
        req.setAttribute("name", name);
        req.setAttribute("messageList", messageService.getFromDestination(login));
        req.getRequestDispatcher("/WEB-INF/jsp/views/ui/user/chats.jsp").forward(req, resp);
    }
}
