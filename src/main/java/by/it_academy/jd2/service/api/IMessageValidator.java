package by.it_academy.jd2.service.api;

import by.it_academy.jd2.core.dto.Message;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;

public interface IMessageValidator {
    boolean validate(Message message) throws ValidatorException;
}
