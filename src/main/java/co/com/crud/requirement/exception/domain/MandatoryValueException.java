package co.com.crud.requirement.exception.domain;

public class MandatoryValueException extends BaseException {
    public MandatoryValueException(String humanMessage) {
        super(humanMessage);
    }
}
