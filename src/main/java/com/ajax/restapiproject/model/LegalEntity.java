package com.ajax.restapiproject.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Superclass for a legal entities
 */
@MappedSuperclass
public class LegalEntity extends BaseEntity{

	/**
	 * Name of legal entity
	 */
	@Column(name = "name", length = 64, nullable = false)
	private String name;

	/**
	 * Phone of legal entity
	 */
	@Column(name = "phone", length = 18)
	private String phone;

	/**
	 * Address of legal entity
	 */
	@Column(name = "address", length = 64, nullable = false)
	private String address;

	/**
	 * Flag of activity of legal entity
	 */
	@Column(name = "is_active")
	private Boolean isActive;
	
	/**
	 * Initialize all new entities as active
	 */
	public LegalEntity() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
