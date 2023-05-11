package com.alan.test.javainterview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler
{
    @ExceptionHandler({
        InvalidReservationDataException.class,
        ReservationNotFoundExceptionException.class,
        Exception.class
    })
    public ResponseEntity<String> handleCustomExceptions(Exception e)
    {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof InvalidReservationDataException
            || e instanceof ReservationNotFoundExceptionException)
        {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(e.getMessage(), status);
    }
}
