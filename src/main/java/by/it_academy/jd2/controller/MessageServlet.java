package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Message;
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
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/api/message")
public class MessageServlet extends HttpServlet {
    private final IMessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/views/ui/user/message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("user");
        String text = req.getParameter("text");
        String destination = req.getParameter("destination");
        if (text == null || destination == null) {
            resp.sendError(400, "Нет текста или не указан адресат");
        }
        messageService.sendMessage(new Message.Builder()
                .setTime(LocalDateTime.now())
                .setAuthor(author)
                .setDestination(req.getParameter("destination"))
                .setText(req.getParameter("text"))
                .build());
        resp.sendRedirect(req.getContextPath() + "/");
    }

}
