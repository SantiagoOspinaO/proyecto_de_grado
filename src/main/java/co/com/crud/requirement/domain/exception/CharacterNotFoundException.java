package co.com.crud.requirement.domain.exception;

public class CharacterNotFoundException extends BaseException{
    private static final String ERROR_MESSAGE = "La caracteristica %d no existe";

    public CharacterNotFoundException(long characterId) {
        super(String.format(ERROR_MESSAGE, characterId));
    }
}
