package by.it_academy.jd2.service;

import by.it_academy.jd2.core.dto.User;
import by.it_academy.jd2.core.dto.UserReg;
import by.it_academy.jd2.core.dto.UserRole;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.api.IUserValidator;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;
import by.it_academy.jd2.storage.api.IUserStorage;

import java.time.Clock;
import java.time.LocalDateTime;


/**
 * Сервис для работы с пользователями.
 * Реализует интерфейс {@link IUserService}.
 * Осуществляет следующие операции:
 * Регистрация нового пользователя;
 * Проверка есть ли в хранилище пользователь с заданными логином и паролем;
 * Получение пользователя по его логину;
 * Подсчёт общего количества пользователей.
 */
public class UserService implements IUserService {
    private final IUserStorage storage;
    private final IUserValidator validator;

    public UserService(IUserStorage storage, IUserValidator validator) {
        this.storage = storage;
        this.validator = validator;
    }

    @Override
    public void add(UserReg user) throws ValidatorException {
        if(validator!=null) if(!validator.isValid(user)) return;

        Clock clock = Clock.systemDefaultZone();
        storage.add(new User.Builder()
                        .setLogin(user.getLogin())
                        .setPassword(user.getPassword())
                        .setName(user.getName())
                        .setDateOfBirth(LocalDateTime.of(
                                Integer.parseInt(user.getYear()),
                                Integer.parseInt(user.getMonth()),
                                Integer.parseInt(user.getDay()),0,0,0))
                        .setDateOfCreate(LocalDateTime.now(clock))
                        .setRole(UserRole.USER)
                        .build()
                );
    }


    @Override
    public User getByLogin(String login) {
        return storage.getByLogin(login);
    }

    @Override
    public int getCount() {
        return storage.getCount();
    }
}
