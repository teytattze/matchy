package com.matchy.app.focusarea.exception;

import com.matchy.app.exception.DomainException;
import com.matchy.app.exception.ErrorResponse;
import com.matchy.app.focusarea.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FocusAreaExceptionHandler {

    @ExceptionHandler(FocusAreaNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(DomainException e) {
        var response = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), e.getCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({
        InvalidDescriptionException.class,
        InvalidNameException.class,
        InvalidRelatedFocusAreasException.class,
        InvalidWeightageException.class,
        RelatedFocusAreaAlreadyExitsException.class
    })
    protected ResponseEntity<ErrorResponse> handleBadRequestException(DomainException e) {
        var response = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), e.getCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
