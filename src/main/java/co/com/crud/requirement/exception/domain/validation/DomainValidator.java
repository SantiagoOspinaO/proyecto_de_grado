package co.com.crud.requirement.exception.domain.validation;

import co.com.crud.requirement.exception.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DomainValidator {
    public static final String LETTERS_ONLY_PATTERN = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ?¿ ]+$";
    public static final String NUMBERS_ONLY_PATTERN = "[0-9]+";
    public static final String NUMBERS_DECIMAL_PATTERN = "^[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)$";
    public static final String LETTERS_AND_NUMBERS_PATTERN = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]+$";
    public static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String FIELD_MANDATORY = "El campo %s es obligatorio";
    public static final String NAME_FIELD_MANDATORY = "El campo nombre es obligatorio";
    public static final String DESCRIPTION_FIELD_MANDATORY = "El campo descripción es obligatorio";
    public static final String TYPE_FIELD_MANDATORY = "El campo tipo de requisito es obligatorio y se debe especificar";
    public static final String DEFAULT_MESSAGE = "Ocurrió un error procesando la solicitud. Por favor pongase en contacto con soporte.";
    public static final String MAX_MIN_NANE_LENGHT_MESSAGE = "El campo nombre tiene una longitud minima de 10 caracteres, y maxima de 50 caracteres, porfavor verifica";

    private DomainValidator() {}

    public static void validateMandatory(Object value, String humanMessage) {
        if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
            throw new MandatoryValueException(humanMessage);
        }
    }

    /*public static void validateMandatory(Object value, String humanMessage) {
        if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
            throw new MandatoryValueException(DEFAULT_MESSAGE);
        }
    }*/

    public static void validateEquals(Object value, Object expectedValue, String humanMessage) {
        if (!expectedValue.equals(value)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateDifferent(Object value, Object unexpectedValue, String humanMessage) {
        if (unexpectedValue.equals(value)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateLessThan(Double valueToBeLower, Double valueToBeHigher, String humanMessage) {
        if (valueToBeLower != null && valueToBeHigher != null && valueToBeLower >= valueToBeHigher) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateGreaterThan(Integer valueToBeHigher, Integer valueToBeLower, String humanMessage) {
        if (valueToBeLower != null && valueToBeHigher != null && valueToBeHigher <= valueToBeLower) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateNumber(String value, String humanMessage) {
        try {
            if (value != null) Double.parseDouble(value);
        } catch (NumberFormatException exception) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validatePositive(Double value, String humanMessage) {
        if (value != null && value <= 0) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateMinLength(String value, int minLength, String humanMessage) {
        if (value != null && value.length() < minLength) {
            throw new MinLengthException(humanMessage);
        }
    }

    public static void validateMaxLength(String value, int maxLength, String humanMessage) {
        if (value != null && value.length() > maxLength) {
            throw new MaxLengthException(humanMessage);
        }
    }

    public static void validateMinMaxLength(String value, int minLength, int maxLength, String humanMessage) {
        validateMinLength(value, minLength, humanMessage);
        validateMaxLength(value, maxLength, humanMessage);
    }

    public static void validateBefore(LocalDate dateToBeBefore, LocalDate dateToBeAfter, String humanMessage) {
        if (dateToBeBefore != null && dateToBeAfter != null && dateToBeBefore.isAfter(dateToBeAfter)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateBefore(LocalDateTime dateToBeBefore, LocalDateTime dateToBeAfter, String humanMessage) {
        if (dateToBeBefore != null && dateToBeAfter != null && dateToBeBefore.isAfter(dateToBeAfter)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateAfter(LocalDate dateToBeAfter, LocalDate dateToBeBefore, String humanMessage) {
        if (dateToBeAfter != null && dateToBeBefore != null && dateToBeAfter.isBefore(dateToBeBefore)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateAfter(LocalDateTime dateToBeAfter, LocalDateTime dateToBeBefore, String humanMessage) {
        if (dateToBeAfter != null && dateToBeBefore != null && dateToBeAfter.isBefore(dateToBeBefore)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static void validateRegex(String value, String regex, String humanMessage) {
        if (value != null && !value.matches(regex)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static <T> void validateNonEmpty(String value, String humanMessage) {
        if (value == null || value.isEmpty()) {
            throw new NoDataException(humanMessage);
        }
    }

    public static <T> void validateContains(T value, List<T> list, String humanMessage) {
        if (list != null && !list.contains(value)) {
            throw new InvalidValueException(humanMessage);
        }
    }

    public static String formattedMessage(String baseMessage, Object... values) {
        return String.format(baseMessage, values);
    }

    public static String mandatoryMessage(String field) {
        return formattedMessage(FIELD_MANDATORY, field);
    }
}
