package by.it_academy.jd2.service.api;

import by.it_academy.jd2.core.dto.User;
import by.it_academy.jd2.core.dto.UserReg;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;

public interface IUserService {
    void add(UserReg user) throws ValidatorException;
    User getByLogin(String login);
    int getCount();
}
