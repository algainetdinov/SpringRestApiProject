package com.ajax.restapiproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ajax.restapiproject.exception.ApiException;
import com.ajax.restapiproject.exception.BadRequestException;
import com.ajax.restapiproject.exception.NotFoundException;
import com.ajax.restapiproject.exception.NotSavedException;
import com.ajax.restapiproject.view.ExceptionView;

@RestControllerAdvice
public class ExceptionHandlerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionView handleNotFoundException (ApiException e) {
		ExceptionView exView = new ExceptionView(e.getMessage());
		return exView;
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionView handleBadRequestException (ApiException e) {
		ExceptionView exView = new ExceptionView(e.getMessage());
		return exView;
	}
	
	@ExceptionHandler(NotSavedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionView handleNotSavedException (ApiException e) {
		ExceptionView exView = new ExceptionView(e.getMessage());
		return exView;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionView handleException (Exception e) {
		logger.error("Exception: ", e);
		ExceptionView exView = new ExceptionView("Internal server error");
		return exView;
	}
}
