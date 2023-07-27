package co.com.crud.requirement.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private String exceptionName;
    private String humanMessage;
}
