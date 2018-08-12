package com.ajax.restapiproject.office.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping office data retrieved by /list request
 */
@ApiModel
public class OfficeListViewReq {

	/**
	 * Office's organization identifier
	 */
	@ApiModelProperty(value = "Office's organization identifier", position = 1, required = true)
	public String orgId;

	/**
	 * Office name
	 */
	@ApiModelProperty(value = "Office name", position = 2)
	public String name;

	/**
	 * Office phone
	 */
	@ApiModelProperty(value = "Office phone", position = 3)
	public String phone;

	/**
	 * Office activity
	 */
	@ApiModelProperty(value = "Office activity", position = 4)
	public String isActive;

	/**
	 * Default constructor
	 */
	public OfficeListViewReq() {
	}
}
