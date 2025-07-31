package by.it_academy.jd2.controller;

import by.it_academy.jd2.core.ContextFactory;
import by.it_academy.jd2.service.StatisticsService;
import by.it_academy.jd2.service.api.IStatisticsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/api/admin/statistics")
public class StatisticServlet extends HttpServlet {
    private final IStatisticsService statisticsService= ContextFactory.getBean(StatisticsService.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Integer> statistic = statisticsService.getStatistics(req.getServletContext());
        req.setAttribute("usersCount", statistic.get("usersCount"));
        req.setAttribute("messagesCount", statistic.get("messagesCount"));
        req.setAttribute("activeUsersCount", statistic.get("activeUsersCount"));
        req.getRequestDispatcher("/WEB-INF/jsp/views/ui/admin/statistics.jsp").forward(req, resp);
    }
}
