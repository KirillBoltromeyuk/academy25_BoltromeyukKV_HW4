package by.it_academy.jd2.service;


import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.api.IStatisticsService;
import by.it_academy.jd2.service.api.IUserService;
import jakarta.servlet.ServletContext;

import java.util.Map;
/**
 * Сервис для получения статистики по пользователям и сообщениям.
 * Реализует интерфейс {@link IStatisticsService}.
 * Предоставляет данные о количестве зарегистрированных пользователей, сообщений и активных сессий.
 */
public class StatisticsService implements IStatisticsService {
    private final IUserService userService=new UserService();
    private final IMessageService messageService=new MessageService();

    @Override
    public Map<String, Integer> getStatistics(ServletContext context) {
        int usersCount = userService.getUsersCount();
        int messagesCount = messageService.getMessagesCount();
        int activeUsersCount = (int) context.getAttribute("activeUsersCount");

        return Map.of(
                "usersCount", usersCount,
                "messagesCount", messagesCount,
                "activeUsersCount", activeUsersCount
        );
    }
}
