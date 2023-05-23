package co.com.crud.requirement.exception.domain;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final String humanMessage;

    protected BaseException(String humanMessage) {
        this.humanMessage = humanMessage;
    }
}
