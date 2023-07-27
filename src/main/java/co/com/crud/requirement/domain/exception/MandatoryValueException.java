package co.com.crud.requirement.domain.exception;

public class MandatoryValueException extends BaseException {
    public MandatoryValueException(String humanMessage) {
        super(humanMessage);
    }
}
