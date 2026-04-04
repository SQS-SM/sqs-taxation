package com.ds.sqs.taxation.api.error;

import com.ds.sqs.taxation.common.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalError(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setStatus(500);
        error.setError("Internal Server Error");
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now().toString());

        return error;
    }
}