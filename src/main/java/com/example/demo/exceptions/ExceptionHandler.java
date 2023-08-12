package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.model.ResponseStatus;


@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(ApiException.class)
	public ResponseStatus<Void> handleAPIExeption(ApiException e) {
		return new ResponseStatus<Void>(e.getStatus(), e.getMessage(), null);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseStatus<Void> handleOtherException(Exception e) {
		return new ResponseStatus<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);

	}

}
