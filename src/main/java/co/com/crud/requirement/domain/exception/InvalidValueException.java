package co.com.crud.requirement.domain.exception;

public class InvalidValueException extends BaseException {
    public InvalidValueException(String humanMessage) {
        super(humanMessage);
    }
}
