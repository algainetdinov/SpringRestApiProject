package com.ajax.restapiproject.office.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping office data retrieved by /update request
 */
@ApiModel
public class OfficeUpdateViewReq {

	/**
	 * Office identifier
	 */
	@ApiModelProperty(value = "Office identifier", position = 1, required = true)
	public String id;

	/**
	 * Office name
	 */
	@ApiModelProperty(value = "Office name", position = 2)
	public String name;

	/**
	 * Office address
	 */
	@ApiModelProperty(value = "Office address", position = 3)
	public String address;

	/**
	 * Office address
	 */
	@ApiModelProperty(value = "Office address", position = 4)
	public String phone;

	/**
	 * Office activity
	 */
	@ApiModelProperty(value = "Office activity", position = 5)
	public String isActive;

	/**
	 * Default constructor
	 */
	public OfficeUpdateViewReq() {
	}

	@Override
	public String toString() {
		return "OfficeUpdateViewReq [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", isActive=" + isActive + "]";
	}
}
