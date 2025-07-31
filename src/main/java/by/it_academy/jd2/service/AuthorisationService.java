package by.it_academy.jd2.service;

import by.it_academy.jd2.service.api.IAuthorisationService;
import by.it_academy.jd2.storage.api.IUserStorage;

public class AuthorisationService implements IAuthorisationService {

    private final IUserStorage storage;

    public AuthorisationService(IUserStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean authoriseUser(String login, String password) {
        return storage.existsByLoginAndPass(login, password);
    }
}
