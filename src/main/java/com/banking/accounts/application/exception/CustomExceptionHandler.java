package com.banking.accounts.application.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private Logger logger;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleBasicExceptions(Exception ex , WebRequest request) {
        ApiError apiError =  new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, null, apiError.getStatus(), request);

    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, "Record Not Found",  ex.getMessage());
        logger.error(apiError.toString());

        return handleExceptionInternal( ex, apiError, null, apiError.getStatus(), request);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNUllPointer(NullPointerException ex, HttpHeaders headers,  WebRequest request) {
        ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),  ex.getMessage());
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, headers, apiError.getStatus(), request);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, "Input Parameter is Invalid", ex.getMessage());
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, headers, apiError.getStatus(), request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Parameter is not valid: ", ex.getMessage());
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, headers, apiError.getStatus(), request);

    }
    @ExceptionHandler({ParseException.class})
    public ResponseEntity<Object> handleParseException(ParseException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Unable to Parse Data Type: " , ex.getMessage());
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, null, apiError.getStatus(), request);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String errorMessage = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorMessage);
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, null, apiError.getStatus(), request);

    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
        logger.error(apiError.toString());
        return handleExceptionInternal( ex, apiError, null, apiError.getStatus(), request);

    }



}