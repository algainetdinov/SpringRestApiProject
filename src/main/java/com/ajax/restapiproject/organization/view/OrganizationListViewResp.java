package com.ajax.restapiproject.organization.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying office data retrieved by /list request
 */
@ApiModel
@JsonPropertyOrder({ "id", "name", "fullname", "inn", "kpp", "address", "phone", "isActive" })
public class OrganizationListViewResp {

	/**
	 * Organization identifier
	 */
	@ApiModelProperty(value = "Organization identifier", position = 1)
	@JsonProperty("id")
	public String id;

	/**
	 * Organization name
	 */
	@ApiModelProperty(value = "Organization name", position = 2)
	@JsonProperty("name")
	public String name;

	/**
	 * Organization activity
	 */
	@ApiModelProperty(value = "Organization activity", position = 3)
	@JsonProperty("isActive")
	public String isActive;

	/**
	 * Constructor for initializing class fields
	 * 
	 * @param id
	 * @param name
	 * @param isActive
	 */
	public OrganizationListViewResp(String id, String name, String isActive) {
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "OrganizationListViewResp [id=" + id + ", name=" + name + ", isActive=" + isActive + "]";
	}
}
