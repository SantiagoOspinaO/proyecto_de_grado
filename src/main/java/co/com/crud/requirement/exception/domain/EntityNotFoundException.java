package co.com.crud.requirement.exception.domain;

public class EntityNotFoundException extends BaseException {
    private static final String ERROR_MESSAGE = "La entidad %d no existe";

    public EntityNotFoundException(long entityId) {
        super(String.format(ERROR_MESSAGE, entityId));
    }
}
