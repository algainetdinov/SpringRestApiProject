package com.ajax.restapiproject.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Superclass for a dictionary classes
 */
@MappedSuperclass
public class DictionaryEntity extends BaseEntity{
	
	/**
	 * Common code field with default length 2
	 */
	@Column(name = "code", unique = true, length = 2, nullable = false)
	private String code;
	
	/**
	 * Common name column with default length 64
	 */
	@Column(name = "name", unique = true, length = 64, nullable = false)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}