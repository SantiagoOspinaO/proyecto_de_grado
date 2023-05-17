package com.crud.democrud.utils.exceptions;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final String humanMessage;

    protected BaseException(String humanMessage) {
        this.humanMessage = humanMessage;
    }
}
