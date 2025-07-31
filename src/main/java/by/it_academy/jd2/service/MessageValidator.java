package by.it_academy.jd2.service;

import by.it_academy.jd2.core.dto.Message;
import by.it_academy.jd2.service.api.IMessageValidator;
import by.it_academy.jd2.service.api.exceptions.ValidatorException;
import by.it_academy.jd2.storage.UserStorage;
import by.it_academy.jd2.storage.api.exceptions.StorageException;

public class MessageValidator implements IMessageValidator {
    private final UserStorage userStorage;

    public MessageValidator(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public boolean validate(Message message) throws ValidatorException {
        if(message==null) throw new ValidatorException("Нет сообщения!");
        if(message.getAuthor().isEmpty()) throw new ValidatorException("Не указан автор!");
        if(message.getDestination().isEmpty()) throw new ValidatorException("Не указан адресат!");
        if(message.getText().isEmpty()) throw new ValidatorException("Сообщение не должно быть пустым!");
        try{
            userStorage.getByLogin(message.getDestination());
        }catch (StorageException e) {
            throw new ValidatorException("Адресат не найден!");
        }
        return true;
    }
}
