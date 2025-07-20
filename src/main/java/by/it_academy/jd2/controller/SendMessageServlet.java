package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Message;
import by.it_academy.jd2.service.MessageService;
import by.it_academy.jd2.service.api.IMessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/sendMessage")
public class SendMessageServlet extends HttpServlet {
    private final IMessageService messageService = new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("user");
        if(login==null) {
            resp.sendRedirect(req.getContextPath() +"/login");
        }
        req.setAttribute("author", login);
        req.getRequestDispatcher("template/sendMessageForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("user");
        if(author==null) {
            resp.sendRedirect(req.getContextPath() +"/login");
        }
        String text = req.getParameter("text");
        String destination = req.getParameter("destination");
        if(text==null || destination==null) {
            resp.sendError(400, "Нет текста или не указан адресат");
        }
        messageService.sendMessage(new Message.Builder()
                        .setTime(LocalDateTime.now())
                        .setAuthor(author)
                        .setDestination(req.getParameter("destination"))
                        .setText(req.getParameter("text"))
                .build());
        resp.sendRedirect(req.getContextPath() +"/messages");
    }
}
