package com.ajax.restapiproject.organization.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying organization data retrieved by ID
 */
@ApiModel
@JsonPropertyOrder({ "id", "name", "fullname", "inn", "kpp", "address", "phone", "isActive" })
public class OrganizationIdViewResp {

	/**
	 * Organization identifier
	 */
	@ApiModelProperty(value = "Organization identifier", position = 1)
	@JsonProperty("id")
	public String id;

	/**
	 * Organization name
	 */
	@ApiModelProperty(value = "name", position = 2)
	@JsonProperty("name")
	public String name;

	/**
	 * Organization full name
	 */
	@ApiModelProperty(value = "fullName", position = 3)
	@JsonProperty("fullName")
	public String fullName;

	/**
	 * Organization INN
	 */
	@ApiModelProperty(value = "inn", position = 4)
	@JsonProperty("inn")
	public String inn;

	/**
	 * Organization KPP
	 */
	@ApiModelProperty(value = "kpp", position = 5)
	@JsonProperty("kpp")
	public String kpp;

	/**
	 * Organization address
	 */
	@ApiModelProperty(value = "address", position = 6)
	@JsonProperty("address")
	public String address;

	/**
	 * Organization phone
	 */
	@ApiModelProperty(value = "phone", position = 7)
	@JsonProperty("phone")
	public String phone;

	/**
	 * Organization activity
	 */
	@ApiModelProperty(value = "isActive", position = 8)
	@JsonProperty("isActive")
	public String isActive;

	/**
	 * Constructor for initializing class fields
	 * 
	 * @param id
	 * @param name
	 * @param fullName
	 * @param inn
	 * @param kpp
	 * @param address
	 * @param phone
	 * @param isActive
	 */
	public OrganizationIdViewResp(String id, String name, String fullName, String inn, String kpp, String address,
			String phone, String isActive) {
		this.id = id;
		this.name = name;
		this.fullName = fullName;
		this.inn = inn;
		this.kpp = kpp;
		this.address = address;
		this.phone = phone;
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "OrganizationIdViewResp [id=" + id + ", name=" + name + ", fullName=" + fullName + ", inn=" + inn
				+ ", kpp=" + kpp + ", address=" + address + ", phone=" + phone + ", isActive=" + isActive + "]";
	}
}
