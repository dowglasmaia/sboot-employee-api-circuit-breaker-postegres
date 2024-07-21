package com.dmaia.software.employeeapi.config;

import com.dmaia.software.employeeapi.exeptions.ObjectNotFoundExeption;
import com.dmaia.software.employeeapi.exeptions.UnprocessableEntityExeption;
import com.dmaia.software.employeeapi.exeptions.model.ValidationError;
import com.dmaia.software.provider.model.ResponseErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExeption.class)
    public final ResponseEntity<ResponseErrorVO> handlerObjectNotFoundExeption(Exception ex) {
        ResponseErrorVO exceptionResponse = new ResponseErrorVO();
        exceptionResponse.setCode(HttpStatus.NOT_FOUND.name());
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<ResponseErrorVO>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableEntityExeption.class)
    public final ResponseEntity<ResponseErrorVO> handlerUnprocessableEntityExeption(Exception ex) {
        ResponseErrorVO exceptionResponse = new ResponseErrorVO();
        exceptionResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.name());
        exceptionResponse.setMessage(ex.getMessage());
        return new ResponseEntity<ResponseErrorVO>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }


  /*  @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResponseErrorVO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ValidationError exceptionResponse = new ValidationError();
        exceptionResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.name());
        exceptionResponse.setMessage("Valid the format of the informed fields!");

        for (FieldError x : ex.getBindingResult().getFieldErrors()) {
            exceptionResponse.addError(x.getField(), x.getDefaultMessage());
        }

        return new ResponseEntity<ResponseErrorVO>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
*/

}
