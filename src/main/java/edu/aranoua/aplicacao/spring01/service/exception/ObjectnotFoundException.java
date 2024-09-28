package edu.aranoua.aplicacao.spring01.service.exception;

public class ObjectnotFoundException extends RuntimeException {
    public ObjectnotFoundException(String message) {
        super(message);
    }
}