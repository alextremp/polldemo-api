package com.schibsted.onepunch.polldemo.infrastructure.controller.rest.handler;

import com.schibsted.onepunch.polldemo.domain.poll.PollNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = PollNotFoundException.class)
    @ResponseBody
    ErrorResponse handleNotFoundError(Exception error) {
        return new ErrorResponse(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = WebExchangeBindException.class)
    @ResponseBody
    ErrorResponse handleInputBindingError(Exception error) {
        return new ErrorResponse(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ServerWebInputException.class)
    @ResponseBody
    ErrorResponse handleInputDecodingError(Exception error) {
        return new ErrorResponse(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    ErrorResponse handleInternalError(Exception error) {
        return new ErrorResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
