package com.cg.oam.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


class ErrorDetails {

	private String message;
        private LocalDate date;
	private String description;
	
	public ErrorDetails()
	{
		
	}

	public ErrorDetails(String message, LocalDate date,String description) {
		super();
		this.message = message;
		this.date=date;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

        public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

@ControllerAdvice
 public class CartNotFoundException extends ResponseEntityExceptionHandler{
	
	ErrorDetails error = new ErrorDetails();
  
        @ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDetails> handleException(CartNotFoundException exception,WebRequest request)
	{
            ErrorDetails details = new ErrorDetails("error",LocalDate.now(),request.getDescription(false));
	    return new ResponseEntity<ErrorDetails>(details,HttpStatus.NOT_FOUND);
	}
}

