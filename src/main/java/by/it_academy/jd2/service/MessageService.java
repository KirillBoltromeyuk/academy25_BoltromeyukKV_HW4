package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Message;
import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.storage.StorageFactory;
import by.it_academy.jd2.storage.api.IMessageStorage;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class MessageService implements IMessageService {
    private final IMessageStorage storage = StorageFactory.getMessageStorage();

    @Override
    public void sendMessage(Message message) {
        storage.add(message);
    }

    @Override
    public List<Message> getFromDestination(String destination) {
        List<Message> messages = storage.getFromDestination(destination);
        messages.sort(new Comparator<Message>() {
            public int compare(Message o1, Message o2) {
                LocalDateTime createDate1 = o1.getTime();
                LocalDateTime createDate2 = o2.getTime();
                if (createDate1.isBefore(createDate2)) {
                    return 1;
                }else if (createDate1.isAfter(createDate2)) {
                    return -1;
                }
                return 0;
            }
        });
        return messages;
    }

    @Override
    public int getMessagesCount() {
        return storage.getMessagesCount();
    }
}
