package org.cia.committee.common.exception;

public class ParameterNotFoundException extends Exception {
    public ParameterNotFoundException(String message) {
        super(message);
    }

    public ParameterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
