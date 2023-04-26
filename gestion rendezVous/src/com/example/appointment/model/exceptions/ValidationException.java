package com.example.appointment.model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException {

    private static final long serialVersionUID = 1L;

    private Map<String, String> errors = new HashMap<>();

    public ValidationException(String msg) {
        super();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String errrorMessage) {
        errors.put(fieldName, errrorMessage);
    }
}
