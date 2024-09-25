package edu.aranoua.aplicacao.spring01.service.exception;

public class ObjectnotFoundException extends RuntimeException {
    public ObjectnotFoundException(String message) {
        super(message);
    }

    public ObjectnotFoundException(Throwable cause) {
        super(cause);
    }

    public ObjectnotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}