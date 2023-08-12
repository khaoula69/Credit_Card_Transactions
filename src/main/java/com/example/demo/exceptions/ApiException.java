package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiException extends Exception {

	
	private Integer status;


	public ApiException(String message) {
		super(message);
		this.status=HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public ApiException(Integer statusCode, String message) {
		super(message);
		this.status = statusCode;
	}

}
