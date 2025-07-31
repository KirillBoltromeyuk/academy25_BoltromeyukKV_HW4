package by.it_academy.jd2.service.api;


import by.it_academy.jd2.core.dto.UserReg;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;

public interface IUserValidator {
    boolean isValid(UserReg user) throws ValidatorException;
}
