package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        Map<String,String> errorMap = new HashMap<>();
        for (ObjectError error:allErrors) {
            String msg = error.getDefaultMessage();
            String field = ((FieldError) error).getField();
            errorMap.put(field,msg);
        }
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Map<String,String>> fileNotFoundException(FileNotFoundException e){
        String message = e.getMessage();
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error",message);
        return new ResponseEntity<>(errorMap,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> multipartException(MultipartException e){
        String message = e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> resourceNotFoundException(ResourceNotFoundException e){
        Map<String,String> errorMap = new HashMap<>();
        String fieldname = e.getFieldName();
        String msg = e.getMessage();
        errorMap.put(fieldname,msg);
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        String message = e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    @ExceptionHandler(UsernotCreateThisPostException.class)
    public ResponseEntity<String> usernotCreateThisPostException(UsernotCreateThisPostException e){
        String message = e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> fileAlreadyExistsException(FileAlreadyExistsException ex){
        String msg = ex.getLocalizedMessage();
        Map<String,String> hm = new HashMap<>();
        hm.put("File already exist, path ",msg);
        return new ResponseEntity<>(hm,HttpStatus.CONFLICT);
    }

}
