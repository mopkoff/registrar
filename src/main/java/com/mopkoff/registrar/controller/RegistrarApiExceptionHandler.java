package com.mopkoff.registrar.controller;


import static com.mopkoff.registrar.model.enums.ErrorType.REQUEST;
import static com.mopkoff.registrar.model.enums.ErrorType.VALIDATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.mopkoff.registrar.exception.NotFoundException;
import com.mopkoff.registrar.model.error.ErrorResponse;
import com.mopkoff.registrar.model.error.InnerError;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;

@RestControllerAdvice(
    assignableTypes = {
        UserController.class,
        AccountController.class
    })
@Slf4j
public class RegistrarApiExceptionHandler {

  private static final String JSR_VALIDATION_MESSAGE = "Validation errors: %s";

  @ExceptionHandler
  @ResponseStatus(NOT_FOUND)
  public ErrorResponse handleNotFoundException(NotFoundException ex) {
    return ErrorResponse.builder()
        .status(NOT_FOUND.value())
        .message(ex.getMessage())
        .type(REQUEST.getType())
        .build();
  }

  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleJsrValidation(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    return ErrorResponse.builder()
        .status(BAD_REQUEST.value())
        .message(String.format(JSR_VALIDATION_MESSAGE, bindingResult.getErrorCount()))
        .errors(getInnerErrors(bindingResult))
        .type(VALIDATION.getType())
        .build();
  }

  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleConversionFailedException(ConversionFailedException ex) {
    return ErrorResponse.builder()
        .status(BAD_REQUEST.value())
        .message(ex.getMessage())
        .type(REQUEST.getType())
        .build();
  }

  private List<InnerError> getInnerErrors(BindingResult bindingResult) {
    Stream<InnerError> fieldErrorsStream = bindingResult.getFieldErrors().stream()
        .map(er -> InnerError.builder()
            .message(String.format("%s %s", er.getField(), er.getDefaultMessage()))
            .target(String.format("%s.%s", er.getObjectName(), er.getField()))
            .build());

    Stream<InnerError> objErrorsStream = bindingResult.getGlobalErrors().stream()
        .map(er -> InnerError.builder()
            .message(er.getDefaultMessage())
            .target(er.getObjectName())
            .build());

    return Stream.concat(fieldErrorsStream, objErrorsStream).collect(Collectors.toList());
  }

  private InnerError getInnerErrors(ConstraintViolation violation) {
    return InnerError.builder()
        .message(violation.getMessage())
        .target(getTarget(violation))
        .build();
  }

  private String getTarget(ConstraintViolation violation) {
    if (Stream.of(violation.getRootBeanClass().getAnnotations())
        .anyMatch(annotation -> annotation instanceof Service)) {
      return StringUtils.substringAfter(violation.getPropertyPath().toString(), ".");
    } else {
      return violation.getPropertyPath().toString();
    }
  }
}
