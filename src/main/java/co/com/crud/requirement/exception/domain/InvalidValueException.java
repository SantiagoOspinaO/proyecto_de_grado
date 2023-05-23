package co.com.crud.requirement.exception.domain;

public class InvalidValueException extends BaseException {
    public InvalidValueException(String humanMessage) {
        super(humanMessage);
    }
}
