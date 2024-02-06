package co.com.crud.requirement.web.exception;

import co.com.crud.requirement.domain.exception.*;
import co.com.crud.requirement.domain.exception.validation.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
    private static final ConcurrentHashMap<String, HttpStatus> STATES = new ConcurrentHashMap<>();

    public RestExceptionHandler() {
        STATES.put(DuplicateException.class.getSimpleName(), HttpStatus.CONFLICT);
        STATES.put(InvalidValueException.class.getSimpleName(), HttpStatus.UNPROCESSABLE_ENTITY);
        STATES.put(MaxLengthException.class.getSimpleName(), HttpStatus.UNPROCESSABLE_ENTITY);
        STATES.put(MinLengthException.class.getSimpleName(), HttpStatus.UNPROCESSABLE_ENTITY);
        STATES.put(NoDataException.class.getSimpleName(), HttpStatus.NOT_FOUND);
        STATES.put(RequirementNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
        STATES.put(MandatoryValueException.class.getSimpleName(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Error> exceptionResolver(BaseException e) {
        HttpStatus status = STATES.get(e.getClass().getSimpleName());
        Error error = new Error(e.getClass().getSimpleName(), e.getHumanMessage());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exceptionResolver(Exception e) {
        Error error = new Error(e.getClass().getSimpleName(), DomainValidator.DEFAULT_MESSAGE);
        LOGGER.error(e.getClass().getSimpleName(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<Error> exceptionResolver(InvalidValueException e) {
        Error error = new Error(e.getClass().getSimpleName(), DomainValidator.NAME_ALREADY_EXISTS);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
