package com.ajax.restapiproject.organization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ajax.restapiproject.model.LegalEntity;

/**
 * Organization class
 */
@Entity
@Table(name = "organization")
public class Organization extends LegalEntity{
	
	/**
	 * Organization's full name
	 */
	@Column(name = "full_name", length = 128, nullable = false)
	private String fullName;
	
	/**
	 * Organization's INN. Length of legal entitiy's INN is 10 symbols
	 */
	@Column(name = "inn", length = 10, nullable = false)
	private String inn;
	
	/**
	 * Organization's KPP
	 */
	@Column(name = "kpp", length = 9, nullable = false)
	private String kpp;
	
	/**
	 * Call superclass constructor in order to initialize isActive field
	 */
	public Organization() {
		super();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getKpp() {
		return kpp;
	}

	public void setKpp(String kpp) {
		this.kpp = kpp;
	}	
}
