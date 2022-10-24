package org.starj.boot.jpa.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleConflict(DataIntegrityViolationException e) {
        log.error("Handled org.springframework.dao.DataIntegrityViolationException : {} ", e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void handleNotFound(EmptyResultDataAccessException e) {
        log.error("Handled org.springframework.dao.EmptyResultDataAccessException : {} ", e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    @ExceptionHandler(NoDataFoundException.class)
    public void handleNoDataFound(NoDataFoundException e) {
        log.error("Handled org.starj.boot.jpa.demo.NoDataFoundException : {} ", e.getLocalizedMessage());
    }

}
