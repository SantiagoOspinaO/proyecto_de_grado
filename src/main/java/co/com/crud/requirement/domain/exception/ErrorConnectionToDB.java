package co.com.crud.requirement.domain.exception;

public class ErrorConnectionToDB extends BaseException {

    private static final String ERROR_MESSAGE = "Error al establecer una conexión con la base de datos";

    public ErrorConnectionToDB(String humanMessage) {
        super(String.format(ERROR_MESSAGE, humanMessage));
    }
}
