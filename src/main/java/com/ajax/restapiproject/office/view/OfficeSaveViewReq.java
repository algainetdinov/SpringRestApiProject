package com.ajax.restapiproject.office.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping office data retrieved by /save request
 */
@ApiModel
public class OfficeSaveViewReq {
	
	/**
	 * Organization to which office belongs
	 */
	@ApiModelProperty(value = "Organization to which office belongs", position = 1, required = true)
	public String orgId;
	
	/**
	 * Office name
	 */
	@ApiModelProperty(value = "Office name", position = 2, required = true)
	public String name;
	
	/**
	 * Office address
	 */
	@ApiModelProperty(value = "Office address", position = 3, required = true)
	public String address;
	
	/**
	 * Office phone
	 */
	@ApiModelProperty(value = "Office phone", position = 4)
	public String phone;
	
	/**
	 * Office activity
	 */
	@ApiModelProperty(value = "Office activity", position = 5)
	public String isActive;

	/**
	 * Default constructor
	 */
	public OfficeSaveViewReq() {
	}

	@Override
	public String toString() {
		return "name=" + name + ", orgId=" + orgId +", address=" + address + ", phone=" + phone + ", isActive="+ isActive;
	}
}