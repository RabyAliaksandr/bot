package com.raby.citybot.controller.exception;

import com.raby.citybot.security.ApiResponse;
import com.raby.citybot.service.exception.CityBotServiceException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@ControllerAdvice
@ComponentScan
public class CityBotExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DATA_INTEGRITY = "Data integrity violation";
    private static final String DATABASE_ERROR = "DataBase error";

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleError(HttpServletRequest req, Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        ApiResponse apiResponse = new ApiResponse(false, errors, new Date());
        apiResponse.setMessage(DATA_INTEGRITY);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity conflict(DataIntegrityViolationException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        ApiResponse apiResponse = new ApiResponse(false, errors, new Date());
        apiResponse.setMessage(DATA_INTEGRITY);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    protected ResponseEntity dataBaseError(DataAccessException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        ApiResponse apiResponse = new ApiResponse(false, errors, new Date());
        apiResponse.setMessage(DATABASE_ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CityBotServiceException.class})
    protected ResponseEntity serviceException(CityBotServiceException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        ApiResponse apiResponse = new ApiResponse(false, errors, new Date());
        apiResponse.setMessage(DATABASE_ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
