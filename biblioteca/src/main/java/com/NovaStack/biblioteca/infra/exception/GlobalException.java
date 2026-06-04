package com.NovaStack.biblioteca.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ResponseError> catchResourceNotFoundException(IllegalStateException ex){
//        System.out.println(ex.getMessage());
//        ResponseError response = new ResponseError(
//                ex.getMessage(),
//                HttpStatus.NOT_FOUND,
//                LocalDateTime.now()
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> catchBusinessException(IllegalStateException ex){
        System.out.println(ex.getMessage());
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> catchGlobalException(Exception ex){
        System.out.println(ex.getMessage());
        ResponseError response = new ResponseError(
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
