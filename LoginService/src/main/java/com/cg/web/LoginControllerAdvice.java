package com.cg.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.dto.ErrorInfo;
import com.cg.exceptions.LoginException;

@RestControllerAdvice
public class LoginControllerAdvice {
	@ExceptionHandler(LoginException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ErrorInfo handleLoginException(LoginException ex) {
		return new ErrorInfo(HttpStatus.FORBIDDEN.toString(), ex.getMessage());
	}
}
