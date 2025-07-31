package by.it_academy.jd2.service.api;

import by.it_academy.jd2.core.dto.Message;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;

import java.util.List;

public interface IMessageService {
    void add(Message message) throws ValidatorException;
    List<Message> getFromDestination(String destination);
    int getCount();
}
