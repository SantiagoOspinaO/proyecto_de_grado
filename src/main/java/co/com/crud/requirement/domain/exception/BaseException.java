package co.com.crud.requirement.domain.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final String humanMessage;

    public BaseException(String humanMessage) {
        this.humanMessage = humanMessage;
    }
}
