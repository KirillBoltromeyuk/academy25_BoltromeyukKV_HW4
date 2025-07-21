package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.User;

import java.time.LocalDateTime;

public interface IUserService {
    void registerUser(User user);
    boolean authoriseUser(String login, String password);
    User getByLogin(String login);
    int getUsersCount();
}
