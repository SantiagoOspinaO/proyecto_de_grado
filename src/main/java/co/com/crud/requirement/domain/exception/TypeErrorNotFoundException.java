package co.com.crud.requirement.domain.exception;

public class TypeErrorNotFoundException extends BaseException {
    private static final String ERROR_MESSAGE = "El error %d no existe";

    public TypeErrorNotFoundException(long mistakeId) {
        super(String.format(ERROR_MESSAGE, mistakeId));
    }
}
