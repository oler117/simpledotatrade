package com.simpledotatrade.controller;

import com.simpledotatrade.domain.exceptions.ErrorResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsController {

    @com.simpledotatrade.misc.loginject.Logger
    private Logger LOG;

    public ExceptionsController() {
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse unknownError(Exception ex) {

        ex.printStackTrace();
        return new ErrorResponse(ex.getMessage());
    }

}
