package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.core.dto.User;

public interface IUserStorage {
    void add(User user);
    User getByLogin(String login);
    boolean existsByLoginAndPass(String login, String password);
    int getCount();
}
