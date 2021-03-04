package org.example.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoMoney.class)
    protected ResponseEntity<AwesomeException> handleNoMoneyException() {
        return new ResponseEntity<>(new AwesomeException("Insufficient funds"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongCurrency.class)
    protected ResponseEntity<AwesomeException> handleWrongCurrencyException() {
        return new ResponseEntity<>(new AwesomeException("Wrong currency"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongUUID.class)
    protected ResponseEntity<AwesomeException> handleWrongUUIDException() {
        return new ResponseEntity<>(new AwesomeException("Wrong UUID"), HttpStatus.NOT_FOUND);
    }
}
