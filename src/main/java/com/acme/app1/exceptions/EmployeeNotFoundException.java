package com.acme.app1.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public EmployeeNotFoundException(String message) {
        this.message = message;
    }
}
