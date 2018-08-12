package com.ajax.restapiproject.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Wrapper view for displaying success responses
 */
@ApiModel
public class DataView {

	/**
	 * Response wrapper
	 */
	@ApiModelProperty(value = "Response wrapper")
	public Object data;

	/**
	 * Constructor for initializing class fields
	 * 
	 * @param data
	 */
	public DataView(Object data) {
		this.data = data;
	}
}
