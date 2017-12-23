package com.tatyanayavkina.api.v1;

import com.tatyanayavkina.exception.EntityNotFoundException;
import com.tatyanayavkina.exception.ObjectNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {

    Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleMethodArgumentNotValidException(EntityNotFoundException e) {
        logger.warn("{}", e.getMessage());

        return e.getMessage();
    }

    @ExceptionHandler(ObjectNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleMethodArgumentNotValidException(ObjectNotValidException e) {
        logger.warn("{}", e.getMessage());

        return e.getMessage();
    }
}
