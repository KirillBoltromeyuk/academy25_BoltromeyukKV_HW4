package by.it_academy.jd2.controller;

import by.it_academy.jd2.core.ContextFactory;
import by.it_academy.jd2.core.dto.Message;
import by.it_academy.jd2.service.MessageService;
import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;
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
    private final IMessageService messageService = ContextFactory.getBean(MessageService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/views/ui/user/message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String author = (String) session.getAttribute("user");
        try{
            messageService.add(new Message.Builder()
                    .setTime(LocalDateTime.now())
                    .setAuthor(author)
                    .setDestination(req.getParameter("destination"))
                    .setText(req.getParameter("text"))
                    .build());
        }catch (ValidatorException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/views/ui/user/message.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }

}
