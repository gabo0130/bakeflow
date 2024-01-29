package com.bake.BakeFLowBackend.config;

import com.bake.BakeFLowBackend.dto.response.BaseResponse;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({Exception.class, RuntimeException.class, IllegalArgumentException.class,
            NullPointerException.class, IllegalStateException.class,BadRequestException.class, MalformedJwtException.class})
    public ResponseEntity<BaseResponse> handleGenericException(Exception ex) {
        System.out.println("Error: " + ex.getMessage());

        HttpStatus status = determineHttpStatus(ex);
        return ResponseEntity.status(status).body(BaseResponse.error(ex.getMessage(), null));
    }
    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof IllegalArgumentException || ex instanceof NullPointerException ||
                ex instanceof IllegalStateException|| ex instanceof BadRequestException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof MalformedJwtException) {
            return HttpStatus.UNAUTHORIZED;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }



}

