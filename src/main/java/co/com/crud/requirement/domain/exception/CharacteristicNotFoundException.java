package co.com.crud.requirement.domain.exception;

public class CharacteristicNotFoundException extends BaseException{
    private static final String ERROR_MESSAGE = "La caracteristica %d no existe";

    public CharacteristicNotFoundException(long characteristicId) {
        super(String.format(ERROR_MESSAGE, characteristicId));
    }
}
