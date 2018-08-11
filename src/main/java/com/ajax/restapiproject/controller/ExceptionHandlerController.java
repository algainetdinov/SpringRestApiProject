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

/**
 * In case of exception return ExceptionView
 * @author Al
 *
 */
@RestControllerAdvice(basePackages="com.ajax.restapiproject")
public class ExceptionHandlerController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Return ExceptionView on NotFoundException
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionView handleNotFoundException (ApiException e) {
		
		ExceptionView exView = new ExceptionView(e.getMessage());
		
		return exView;
	}
	
	/**
	 * Return ExceptionView on BadRequestException
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionView handleBadRequestException (ApiException e) {
		
		ExceptionView exView = new ExceptionView(e.getMessage());
		
		return exView;
	}
	
	/**
	 * Return ExceptionView on NotSavedException
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NotSavedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionView handleNotSavedException (ApiException e) {
		
		ExceptionView exView = new ExceptionView(e.getMessage());
		
		return exView;
	}
	
	/**
	 * Return ExceptionView on any other exception
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionView handleException (Exception e) {
		
		logger.error("Exception: ", e);
		
		ExceptionView exView = new ExceptionView("Internal server error");
		
		return exView;
	}
}
