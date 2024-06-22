package com.convention.commons;

import com.convention.commons.model.ExceptionModel;
import com.convention.domain.exceptions.ConvencionException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

import static com.convention.domain.exceptions.ConvenctionTextExecptions.ALREADY_SAVE;
import static com.convention.domain.exceptions.ConvenctionTextExecptions.NOT_FOUND_BD;

@ControllerAdvice
public class ConferenceAdvice {
    @ExceptionHandler({
            HttpMessageNotWritableException.class,
            InvalidDataAccessApiUsageException.class,
            UnsupportedOperationException.class,
            EntityNotFoundException.class,
            JpaSystemException.class,
            NullPointerException.class,
            UnexpectedTypeException.class,
            DataIntegrityViolationException.class,
            ConvencionException.class,
            ConstraintViolationException.class})
    public ResponseEntity<ExceptionModel> exception(Exception exception) {
        return buildResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ExceptionModel> exception(HttpMessageNotReadableException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionModel> exception(NoResourceFoundException exception) {
        return buildResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    private String messageVerify(String message) {
        if (message.contains("constraint [null]")) {
            return NOT_FOUND_BD;
        }

        if (message.contains("Duplicate")) {
            return ALREADY_SAVE;
        }

        return message;
    }

    private ResponseEntity<ExceptionModel> buildResponse(String message, HttpStatus status) {
        return new ResponseEntity<ExceptionModel>(ExceptionModel
                .builder()
                .message(messageVerify(message))
                .timestamp(LocalDateTime.now())
                .error(status.name())
                .status(status.value())
                .build(), status);
    }
}
