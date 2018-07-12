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
	@Column(name = "address", length = 18, nullable = false)
	private String address;

	/**
	 * Flag of activity of legal entity
	 */
	@Column(name = "is_active")
	private boolean isActive;
	
	/**
	 * Initialize all new entities as active
	 */
	public LegalEntity() {
		this.isActive = true;
	}

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
