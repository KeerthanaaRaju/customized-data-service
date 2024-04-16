package data.service.controllers;

import data.service.exceptions.DataServiceException;
import data.service.constants.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class DataServiceExceptionHandler
{
    @ExceptionHandler(DataServiceException.class)
    public ResponseEntity<String> handleDataServiceException(DataServiceException e, WebRequest request) {

        return new ResponseEntity<>(e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e, WebRequest request) {

        return new ResponseEntity<>(Constants.DUPLICATE_KEY, HttpStatus.BAD_REQUEST);
    }
}