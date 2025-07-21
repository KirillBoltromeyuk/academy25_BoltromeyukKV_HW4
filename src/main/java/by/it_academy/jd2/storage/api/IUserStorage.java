package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.User;

public interface IUserStorage {
    void addUser(User user);
    User getUserByLogin(String login);
    boolean UserExists(String login, String password);
    int getUsersCount();
}
