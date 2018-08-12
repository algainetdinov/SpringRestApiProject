package com.ajax.restapiproject.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying success status
 */
@ApiModel
public class SuccessView {

	/**
	 * Success view
	 */
	@ApiModelProperty(value = "Request result")
	public String result;

	/**
	 * Constructor for initializing class fields
	 * 
	 * @param result
	 */
	public SuccessView(String result) {
		this.result = result;
	}
}
