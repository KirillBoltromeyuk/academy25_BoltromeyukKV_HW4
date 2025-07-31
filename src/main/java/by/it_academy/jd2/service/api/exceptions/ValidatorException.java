package by.it_academy.jd2.service.api.exceptions;

public class ValidatorException extends Exception {
    public ValidatorException(String message) {
        super(message);
    }
    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
