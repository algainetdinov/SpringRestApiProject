package com.ajax.restapiproject.organization.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping organization data retrieved by /list request
 */
@ApiModel
public class OrganizationListViewReq {
	
	/**
	 * Organization name
	 */
	@ApiModelProperty(value = "Organization name", position = 1, required = true)
	public String name;
	
	/**
	 * Organization INN
	 */
	@ApiModelProperty(value = "Organization INN", position = 2)
	public String inn;
	
	/**
	 * Organization activity
	 */
	@ApiModelProperty(value = "Organization activity", position = 3)
	public String isActive;
	
	/**
	 * Default constructor
	 */
	public OrganizationListViewReq() {}

	@Override
	public String toString() {
		return "OrganizationListViewReq [name=" + name + ", inn=" + inn + ", isActive=" + isActive + "]";
	}
}
