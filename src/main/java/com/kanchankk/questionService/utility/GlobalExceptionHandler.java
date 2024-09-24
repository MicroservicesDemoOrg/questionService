package com.kanchankk.questionService.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.kanchankk.questionService.to.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ErrorMessage> handleExceptionForStatusCode(HttpStatusCodeException e){
		ErrorMessage error = new ErrorMessage(String.valueOf(e.getStatusCode().value()), e.getStatusCode().toString().substring(4), e.getMessage());
		
		return ResponseEntity.status(e.getStatusCode()).body(error);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
    	ErrorMessage errorMessage = new ErrorMessage(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "Internal Server Error",	e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
	
}
