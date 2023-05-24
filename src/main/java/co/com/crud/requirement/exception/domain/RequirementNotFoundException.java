package co.com.crud.requirement.exception.domain;

public class RequirementNotFoundException extends BaseException {
    private static final String ERROR_MESSAGE = "El requisito %d no existe";

    public RequirementNotFoundException(long entityId) {
        super(String.format(ERROR_MESSAGE, entityId));
    }
}
