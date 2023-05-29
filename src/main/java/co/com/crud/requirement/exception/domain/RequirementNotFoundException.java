package co.com.crud.requirement.exception.domain;

public class RequirementNotFoundException extends BaseException {
    private static final String ERROR_MESSAGE = "El requisito %d no existe";

    public RequirementNotFoundException(long requirementId) {
        super(String.format(ERROR_MESSAGE, requirementId));
    }
}
