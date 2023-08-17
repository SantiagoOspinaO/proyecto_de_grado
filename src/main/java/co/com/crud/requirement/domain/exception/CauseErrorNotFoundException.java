package co.com.crud.requirement.domain.exception;

public class CauseErrorNotFoundException extends BaseException {
    private static final String ERROR_MESSAGE = "La causa de error %d no existe";

    public CauseErrorNotFoundException(long causeErrorId) {
        super(String.format(ERROR_MESSAGE, causeErrorId));
    }
}
