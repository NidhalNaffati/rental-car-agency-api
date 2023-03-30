package com.CarRentalAgency.exception;

import lombok.extern.slf4j.Slf4j;
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

import jakarta.validation.UnexpectedTypeException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

/**
 * This class is a Controller Advice that handles exceptions thrown by the REST API endpoints.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException that is thrown when a method argument annotated with @Valid fails validation.
     *
     * @param exception the MethodArgumentNotValidException to be handled
     * @return a ResponseEntity containing an ErrorResponse object with a
     * status code of NOT_ACCEPTABLE and a message describing the validation error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<String> errorList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String errorMessage = "Validation error : " + errorList;
        log.error(errorMessage, exception);

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.NOT_ACCEPTABLE);
        response.setMessage(errorMessage);
        return buildResponseEntity(response);
    }


    /**
     * Handles TypeMismatchException that is thrown when a method argument is not of the expected type.
     *
     * @param exception the TypeMismatchException to be handled
     * @return a ResponseEntity containing an ErrorResponse object with a status code of NOT_IMPLEMENTED and a message describing the type mismatch error
     */
    @ExceptionHandler(TypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception) {
        String errorMessage = "Type mismatch error: " + exception.getPropertyName() + " should be of type " + exception.getRequiredType();
        log.error(errorMessage, exception);

        ErrorResponse response = new ErrorResponse();
        response.setStatus(NOT_IMPLEMENTED);
        response.setMessage(exception.getMessage());
        return buildResponseEntity(response);
    }


    /**
     * Handles NoSuchElementException that is thrown when an entity is not found in the database.
     *
     * @param exception the NoSuchElementException to be handled
     * @return a ResponseEntity containing an ErrorResponse object with a status code of NOT_FOUND and a message describing the entity not found error
     */
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleEntityNotFound(NoSuchElementException exception) {
        String errorMessage = "Entity not found error: " + exception.getMessage();
        log.error(errorMessage, exception);

        ErrorResponse response = new ErrorResponse();
        response.setStatus(NOT_FOUND);
        response.setMessage(exception.getMessage());
        return buildResponseEntity(response);
    }


    /**
     * Handles AlreadyExistsException that is thrown when an entity already exists in the database.
     *
     * @param exception the AlreadyExistsException to be handled
     * @return a ResponseEntity containing an ErrorResponse object with a status code of CONFLICT and a message describing the entity already exists error
     */
    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<Object> handleEntityDuplicated(AlreadyExistsException exception) {
        String errorMessage = "Entity already exists error: " + exception.getMessage();
        log.error(errorMessage, exception);

        ErrorResponse response = new ErrorResponse();
        response.setStatus(CONFLICT);
        response.setMessage(exception.getMessage());
        return buildResponseEntity(response);
    }

    /**
     * Handles HttpMessageNotReadableException that is thrown when the request body is malformed.
     *
     * @return a ResponseEntity containing an ErrorResponse object with a status code of BAD_REQUEST and a message describing the malformed JSON request error
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable() {
        String errorMessage = "Malformed JSON request";
        log.error(errorMessage);

        ErrorResponse response = new ErrorResponse();
        response.setStatus(BAD_REQUEST);
        response.setMessage(errorMessage);
        return buildResponseEntity(response);
    }


    /**
     * Handles UnexpectedTypeException that is thrown when an object has an unexpected type.
     *
     * @param exception the UnexpectedTypeException to be handled
     * @return a ResponseEntity containing an ErrorResponse object with a status code of BAD_REQUEST and an error message describing the unexpected type error
     */
    @ExceptionHandler(UnexpectedTypeException.class)
    protected ResponseEntity<Object> handleUnexpectedType(UnexpectedTypeException exception) {
        String errorMessage = "Unexpected type error: " + exception.getMessage();
        log.error(errorMessage, exception);

        ErrorResponse response = new ErrorResponse();
        response.setStatus(BAD_REQUEST);
        response.setMessage(errorMessage);
        return buildResponseEntity(response);
    }


    /**
     * Creates a ResponseEntity object from the given ErrorResponse object with the status code and message.
     *
     * @param response the ErrorResponse object to build the ResponseEntity from
     * @return a ResponseEntity object with the status code and message from the ErrorResponse object
     */
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse response) {
        return new ResponseEntity<>(response, response.getStatus());
    }

}