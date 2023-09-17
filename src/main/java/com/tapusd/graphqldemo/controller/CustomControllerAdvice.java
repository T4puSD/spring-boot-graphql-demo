package com.tapusd.graphqldemo.controller;

import com.tapusd.graphqldemo.dto.BaseResponse;
import com.tapusd.graphqldemo.exception.DuplicateValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomControllerAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(new BaseResponse<>()
                        .setCode(status.value())
                        .setMessage(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateValueException.class)
    public ResponseEntity<BaseResponse<Object>> handleDuplicateValueException(DuplicateValueException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(new BaseResponse<>()
                        .setCode(status.value())
                        .setMessage(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGlobalException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(new BaseResponse<>()
                        .setCode(status.value())
                        .setMessage("Internal Server Error. Please Try Again Later."));
    }
}
