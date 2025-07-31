package by.it_academy.jd2.service;

import by.it_academy.jd2.core.dto.Message;
import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.api.IMessageValidator;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;
import by.it_academy.jd2.storage.api.IMessageStorage;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
/**
 * Сервис для отправки и получения сообщений.
 * Реализует интерфейс {@link IMessageService}.
 * Осуществляет следующие операции:
 * Создание нового сообщения;
 * Получение листа сообщений отправленных определённому пользователю;
 * Получение общего количества сообщений.
 */
public class MessageService implements IMessageService {
    private final IMessageStorage storage;
    private final IMessageValidator validator;

    public MessageService(IMessageStorage storage, IMessageValidator validator) {
        this.storage = storage;
        this.validator = validator;
    }

    @Override
    public void add(Message message) throws ValidatorException {
        if(validator!=null) if(!validator.validate(message)) return;
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
    public int getCount() {
        return storage.getCount();
    }
}
