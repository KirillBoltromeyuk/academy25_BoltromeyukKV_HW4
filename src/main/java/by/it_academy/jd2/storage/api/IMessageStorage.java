package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Message;

import java.util.List;

public interface IMessageStorage {
    void add(Message message);
    List<Message> getFromDestination(String destination);
}
