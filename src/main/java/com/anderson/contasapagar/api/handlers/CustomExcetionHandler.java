package com.anderson.contasapagar.api.handlers;

import com.anderson.contasapagar.api.dto.ErrorResponse;
import com.anderson.contasapagar.infra.execeptions.ContaNotFoundExcetion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExcetionHandler {

    @ExceptionHandler(ContaNotFoundExcetion.class)
    public ResponseEntity<ErrorResponse> handleCustomException(ContaNotFoundExcetion ex) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getStatus());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
