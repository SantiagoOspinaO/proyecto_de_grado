package co.com.crud.requirement.domain.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(String humanMessage) {
        super(humanMessage);
    }
}
