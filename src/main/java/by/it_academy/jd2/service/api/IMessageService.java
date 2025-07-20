package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Message;

import java.util.List;

public interface IMessageService {
    void sendMessage(Message message);
    List<Message> getFromDestination(String destination);
}
