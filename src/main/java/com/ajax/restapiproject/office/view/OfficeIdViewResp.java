package com.ajax.restapiproject.office.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying office data retrieved by ID
 */
@ApiModel
public class OfficeIdViewResp {

	/**
	 * Office identifier
	 */
	@ApiModelProperty(value = "Office identifier", position = 1)
	public String id;

	/**
	 * Office name
	 */
	@ApiModelProperty(value = "Office name ", position = 2)
	public String name;

	/**
	 * Office address
	 */
	@ApiModelProperty(value = "Office address", position = 4)
	public String address;

	/**
	 * Office phone
	 */
	@ApiModelProperty(value = "Office phone", position = 5)
	public String phone;

	/**
	 * Office activity
	 */
	@ApiModelProperty(value = "Office activity", position = 6)
	public String isActive;

	/**
	 * Constructor for initializing class fields
	 * 
	 * @param id
	 * @param name
	 * @param address
	 * @param phone
	 * @param isActive
	 */
	public OfficeIdViewResp(String id, String name, String address, String phone, String isActive) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.isActive = isActive;
	}
}
