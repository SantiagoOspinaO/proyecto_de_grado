package co.com.crud.requirement.exception.domain;

public class DuplicateException extends BaseException {
    public DuplicateException(String humanMessage) {
        super(humanMessage);
    }
}
