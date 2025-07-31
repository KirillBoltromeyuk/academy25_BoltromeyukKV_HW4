package by.it_academy.jd2.service;

import by.it_academy.jd2.core.dto.UserReg;
import by.it_academy.jd2.service.api.IUserValidator;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;
import by.it_academy.jd2.storage.api.IUserStorage;
import by.it_academy.jd2.storage.api.exceptions.StorageException;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserValidator implements IUserValidator {
    private final IUserStorage userStorage;

    public UserValidator(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public boolean isValid(UserReg user) throws ValidatorException {
        if (user == null) throw new ValidatorException("User is null");
        if(user.getLogin().isEmpty()) throw new ValidatorException("Логин не должен быть пустым!");
        if(user.getPassword().isEmpty()) throw new ValidatorException("Пароль не должен быть пустым!");
        if(user.getName().isEmpty())throw new ValidatorException("ФИО не должно быть пустым!");
        if(user.getYear().isEmpty()) throw new ValidatorException("Год не должен быть пустым!");
        if(user.getMonth().isEmpty()) throw new ValidatorException("Месяц не должен быть пустым!");
        if(user.getDay().isEmpty()) throw new ValidatorException("День не должен быть пустым!");
        try{
            int year = Integer.parseInt(user.getYear());
            int month = Integer.parseInt(user.getMonth());
            int day = Integer.parseInt(user.getDay());
            if(year<1900 || year> LocalDateTime.now().getYear()) throw new ValidatorException("Неверный год!");
            if(month<=0 || month> 12) throw new ValidatorException("Неверный месяц!");
            if(day<=0 || day> 31) throw new ValidatorException("Неверный день!");
        } catch (NumberFormatException e) {
            throw new ValidatorException("Неверный формат даты!");
        }
        try{
            if(userStorage.getByLogin(user.getLogin())!=null) throw new ValidatorException("Пользователь с таким логином уже существует!");
        }catch (StorageException ignored){}

        if(user.getPassword().length()<6 || user.getPassword().length()>30) throw new ValidatorException("Пароль должен быть от 6 до 30 символов!");
        if(user.getLogin().length()<4 || user.getLogin().length()>30) throw new ValidatorException("Логин должен быть от 4 до 30 символов!");
        if(user.getName().length()<4 || user.getName().length()>30) throw new ValidatorException("ФИО должно быть от 4 до 30 символов!");
        return true;
    }
}
