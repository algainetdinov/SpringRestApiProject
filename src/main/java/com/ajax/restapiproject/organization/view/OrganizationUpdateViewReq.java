package com.ajax.restapiproject.organization.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping organization data retrieved by /update request
 */
@ApiModel
public class OrganizationUpdateViewReq {
	
	/**
	 * Organization identifier
	 */
	@ApiModelProperty(value = "Organization identifier", position = 1, required = true)
	public String id;
	
	/**
	 * Organization name
	 */
	@ApiModelProperty(value = "Organization name", position = 2, required = true)
	public String name;
	
	/**
	 * Organization full name
	 */
	@ApiModelProperty(value = "Organization full name", position = 3, required = true)
	public String fullName;
	
	/**
	 * Organization INN
	 */
	@ApiModelProperty(value = "Organization INN", position = 4, required = true)
	public String inn;
	
	/**
	 * Organization KPP
	 */
	@ApiModelProperty(value = "Organization KPP", position = 5, required = true)
	public String kpp;
	
	/**
	 * Organization address
	 */
	@ApiModelProperty(value = "Organization address", position = 6, required = true)
	public String address;
	
	/**
	 * Organization phone
	 */
	@ApiModelProperty(value = "Organization phone", position = 7)
	public String phone;
	
	/**
	 * Organization activity
	 */
	@ApiModelProperty(value = "Organization activity", position = 8)
	public String isActive;

	/**
	 * Default constructor
	 */
	public OrganizationUpdateViewReq() {
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", fullName=" + fullName + ", inn=" + inn
				+ ", kpp=" + kpp + ", address=" + address + ", phone=" + phone + ", isActive=" + isActive;
	}
	
	
}
