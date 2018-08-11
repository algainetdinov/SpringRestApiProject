package com.ajax.restapiproject.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ajax.restapiproject.view.DataView;
import com.ajax.restapiproject.view.ExceptionView;

/**
 * Wraps all non exception responses in data object
 * @author Al
 *
 */
@RestControllerAdvice(basePackages="com.ajax.restapiproject")
public class JsonResponseWrapper implements ResponseBodyAdvice<Object>{
	
	    @Override
	    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
	        return true;
	    }
	    
	    /**
	     * In case of exception send a ExceptionView otherwise wrap response in a DataView
	     */
	    @Override
	    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, 
	    		Class<? extends HttpMessageConverter<?>> converterType, ServerHttpRequest request, ServerHttpResponse response) {
	    	
	        if (body instanceof ExceptionView) {
	           return body;
	        }
	        
	        else {
	        	return new DataView(body);
	        }
	    }
}
