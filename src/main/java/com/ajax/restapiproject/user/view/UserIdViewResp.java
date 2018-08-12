package com.ajax.restapiproject.user.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying user data retrieved by ID
 */
@ApiModel
@JsonPropertyOrder({ "id", "firstName", "lastName", "middleName", "position", "officeId", "phone", "docCode", "docName",
		"docNumber", "docDate", "citizenshipName", "citizenshipCode", "isIdentified" })
public class UserIdViewResp {

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
	@ApiModelProperty(value = "middle name", position = 4)
	@JsonProperty("middleName")
	public String middleName;

	/**
	 * User position
	 */
	@ApiModelProperty(value = "User position", position = 5)
	@JsonProperty("position")
	public String position;

	/**
	 * User's office id
	 */
	@ApiModelProperty(value = "User's office id", position = 6)
	@JsonProperty("officeId")
	public String officeId;

	/**
	 * User phone
	 */
	@ApiModelProperty(value = "User phone", position = 7)
	@JsonProperty("phone")
	public String phone;

	/**
	 * User's document code
	 */
	@ApiModelProperty(value = "User's document code", position = 8)
	@JsonProperty("docCode")
	public String docCode;

	/**
	 * User's document name
	 */
	@ApiModelProperty(value = "User's document name", position = 9)
	@JsonProperty("docName")
	public String docName;

	/**
	 * User's document number
	 */
	@ApiModelProperty(value = "User's document number", position = 10)
	@JsonProperty("docNumber")
	public String docNumber;

	/**
	 * User's document date
	 */
	@ApiModelProperty(value = "User's document date", position = 11)
	@JsonProperty("docDate")
	public String docDate;

	/**
	 * User's country
	 */
	@ApiModelProperty(value = "User's country", position = 12)
	@JsonProperty("citizenshipName")
	public String citizenshipName;

	/**
	 * User's country code
	 */
	@ApiModelProperty(value = "User's country code", position = 13)
	@JsonProperty("citizenshipCode")
	public String citizenshipCode;

	/**
	 * User identification
	 */
	@ApiModelProperty(value = "User identification", position = 14)
	@JsonProperty("isIdentified")
	public String isIdentified;

	/**
	 * Constructor using fields
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param position
	 * @param officeId
	 * @param phone
	 * @param docCode
	 * @param docName
	 * @param docNumber
	 * @param docDate
	 * @param citizenshipName
	 * @param citizenshipCode
	 * @param isIdentified
	 */
	public UserIdViewResp(String id, String firstName, String lastName, String middleName, String position,
			String officeId, String phone, String docCode, String docName, String docNumber, String docDate,
			String citizenshipName, String citizenshipCode, String isIdentified) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.position = position;
		this.officeId = officeId;
		this.phone = phone;
		this.docCode = docCode;
		this.docName = docName;
		this.docNumber = docNumber;
		this.docDate = docDate;
		this.citizenshipName = citizenshipName;
		this.citizenshipCode = citizenshipCode;
		this.isIdentified = isIdentified;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "UserIdViewResp [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", position=" + position + ", officeId=" + officeId + ", phone=" + phone + ", docCode="
				+ docCode + ", docName=" + docName + ", docNumber=" + docNumber + ", docDate=" + docDate
				+ ", citizenshipName=" + citizenshipName + ", citizenshipCode=" + citizenshipCode + ", isIdentified="
				+ isIdentified + "]";
	}
}
