package com.CarRentalAgency.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.NOT_ACCEPTABLE);
        response.setMessage("Validation error : " + errorList);
        return buildResponseEntity(response);
    }


    @ExceptionHandler(TypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(NOT_IMPLEMENTED);
        response.setMessage(ex.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<Object> handleDuplicatedEntity(SQLIntegrityConstraintViolationException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(BAD_REQUEST);
        response.setMessage(ex.getMessage());
        return buildResponseEntity(response);
    }


    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleEntityNotFound(NoSuchElementException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(NOT_FOUND);
        response.setMessage(ex.getMessage());
        return buildResponseEntity(response);
    }


    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<Object> handleEntityDuplicated(AlreadyExistsException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(CONFLICT);
        response.setMessage(ex.getMessage());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(BAD_REQUEST);
        response.setMessage("Malformed JSON request");
        return buildResponseEntity(response);
    }


    @ExceptionHandler(UnexpectedTypeException.class)
    protected ResponseEntity<Object> handleUnexpectedType(UnexpectedTypeException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(BAD_REQUEST);
        response.setMessage(exception.getMessage());
        return buildResponseEntity(response);
    }


    private ResponseEntity<Object> buildResponseEntity(ErrorResponse response) {
        return new ResponseEntity<>(response, response.getStatus());
    }

}