package com.bridgelabz.bookstore.exception.exceptionhandler;

import com.bridgelabz.bookstore.exception.OrderNotFoundException;
import com.bridgelabz.bookstore.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Response> handleHiringException(OrderNotFoundException he) {
        Response response = new Response();
        response.setErrorCode(400);
        response.setMessage("Admin not found");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
