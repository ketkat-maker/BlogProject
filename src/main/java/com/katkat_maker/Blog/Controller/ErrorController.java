package com.katkat_maker.Blog.Controller;

import com.katkat_maker.Blog.Domain.Dtos.ApiError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestController
@RestControllerAdvice
@Slf4j
public class ErrorController {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handelException(Exception e){
//        log.error("Caught Exception {}", String.valueOf(e));
//        ApiError error = ApiError.builder().
//                status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message("Unexpected Error occurred " + e.getMessage())
//                .build();
//
//        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handelIllegalArgumentException( IllegalArgumentException e,HttpServletRequest request){

        ApiError error = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timeStamp(String.valueOf(LocalDateTime.now()))
                .build();
        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handelIllegalStateException(IllegalArgumentException e , HttpServletRequest request){

        ApiError error = ApiError.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timeStamp(String.valueOf(LocalDateTime.now()))
                .build();
        return  new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handelBadCredentialsException( BadCredentialsException e,HttpServletRequest request){

        ApiError error = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Incorrect Password or Username")
                .path(request.getRequestURI())
                .timeStamp(String.valueOf(LocalDateTime.now()))
                .build();
        return  new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handelEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request){

        ApiError error = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timeStamp(String.valueOf(LocalDateTime.now()))
                .build();
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ApiError>handelHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e,HttpServletRequest request){
        ApiError error = ApiError.builder()
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timeStamp(String.valueOf(LocalDateTime.now()))
                .build();
        return  new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }
}

