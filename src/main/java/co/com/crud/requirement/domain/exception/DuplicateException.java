package co.com.crud.requirement.domain.exception;

public class DuplicateException extends BaseException {
    public DuplicateException(String humanMessage) {
        super(humanMessage);
    }
}
