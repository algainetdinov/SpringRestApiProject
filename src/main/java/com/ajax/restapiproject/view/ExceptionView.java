package com.ajax.restapiproject.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying exception
 */
@ApiModel
public class ExceptionView {
	
	/**
	 * Error response
	 */
	@ApiModelProperty(value = "Error message")
	public String error;
	
	/**
	 * Constructor for initializing class fields 
	 * @param error
	 */
	public ExceptionView(String error) {
		this.error = error;
	}
}
