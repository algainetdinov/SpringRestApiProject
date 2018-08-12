package com.ajax.restapiproject.user.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying user data retrieved by /list request
 */
@ApiModel
@JsonPropertyOrder({ "id", "firstName", "lastName", "middleName", "position" })
public class UserListViewResp {

	/**
	 * User identifier
	 */
	@ApiModelProperty(value = "User identifier", position = 1)
	@JsonProperty("id")
	public String id;

	/**
	 * User first name
	 */
	@ApiModelProperty(value = "User first name", position = 2)
	@JsonProperty("firstName")
	public String firstName;

	/**
	 * User last name
	 */
	@ApiModelProperty(value = "User last name", position = 3)
	@JsonProperty("lastName")
	public String lastName;

	/**
	 * User middle name
	 */
	@ApiModelProperty(value = "User middle name", position = 4)
	@JsonProperty("middleName")
	public String middleName;

	/**
	 * User position
	 */
	@ApiModelProperty(value = "User position", position = 5)
	@JsonProperty("position")
	public String position;

	/**
	 * Constructor for initializing class fields
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param position
	 */
	public UserListViewResp(String id, String firstName, String lastName, String middleName, String position) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.position = position;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "UserListViewResp [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", position=" + position + "]";
	}
}