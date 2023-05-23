package co.com.crud.requirement.exception.domain;

public class NotFoundException extends BaseException {
    public NotFoundException(String humanMessage) {
        super(humanMessage);
    }
}
