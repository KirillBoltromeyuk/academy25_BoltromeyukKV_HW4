package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.User;
import by.it_academy.jd2.dto.UserRole;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.storage.StorageFactory;
import by.it_academy.jd2.storage.api.IUserStorage;

import java.time.LocalDateTime;

public class UserService implements IUserService {
    private final IUserStorage storage=StorageFactory.getUserStorage();

    @Override
    public void registerUser(User user) {
        storage.addUser(user);
    }

    @Override
    public boolean authoriseUser(String login, String password) {
        return storage.UserExists(login, password);
    }

    @Override
    public User getByLogin(String login) {
        return storage.getUserByLogin(login);
    }
}
