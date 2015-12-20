package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.service.exceptions.SaesServiceException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    Error handleException(ResourceNotFoundException ex) {
        return new Error("The requested resource doesn't exist. ");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Error handleException(InvalidParameterException ex) {
        return new Error("The request contains an invalid parameter(s): " + ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    Error handleException(SaesServiceException ex) {
        return new Error(ex.getMessage() + Arrays.toString(ex.getStackTrace()));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class Error {
        private String error;

        public Error(String error) {
            this.error = error;
        }
    }
}