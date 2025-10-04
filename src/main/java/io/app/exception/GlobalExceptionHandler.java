package io.app.exception;

import io.app.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleResourceNotFoundException(Exception ex){
        return ApiResponse.builder()
                .status(false)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(NotMatchingException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse handleNotMatchingException(Exception ex){
        return ApiResponse.builder()
                .message(ex.getMessage())
                .status(false)
                .build();
    }

    @ExceptionHandler(NoLongerAvailableException.class)
    @ResponseStatus(HttpStatus.GONE)
    public ApiResponse handleException(Exception ex){
        return ApiResponse.builder()
                .message(ex.getMessage())
                .status(false)
                .build();
    }
}
