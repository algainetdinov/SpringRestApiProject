package com.ajax.restapiproject.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Person superclass
 */
@MappedSuperclass
public class Person extends BaseEntity{
	
	/**
	 * Person's first name
	 */
	@Column(name = "first_name", length = 32, nullable = false)
	private String firstName;
	
	/**
	 * Person's last name
	 */
	@Column(name = "last_name", length = 32)
	private String lastName;
	
	/**
	 * Person's middle name
	 */
	@Column(name = "middle_name", length = 32)
	private String middleName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}	
}
