package edu.aranoua.aplicacao.spring01.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.aranoua.aplicacao.spring01.model.exception.Message;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExaceptionHandler {
    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<Message> objectNotFound(ObjectnotFoundException e, HttpServletRequest request) {
        Message message = new Message(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Message> runtimeException(RuntimeException e, HttpServletRequest request){
        Message message = new Message();
        message.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setMessage(e.getMessage());
        return ResponseEntity.internalServerError().body(message);
    }
}
