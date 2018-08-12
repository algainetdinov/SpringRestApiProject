package com.ajax.restapiproject.organization.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ajax.restapiproject.model.LegalEntity;
import com.ajax.restapiproject.office.model.Office;

/**
 * Organization entity
 */
@Entity
@Table(name = "organization")
public class Organization extends LegalEntity {

	/**
	 * Organization's full name
	 */
	@Column(name = "full_name", length = 64, nullable = false)
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
	 * List of related offices
	 */
	@OneToMany(mappedBy = "org", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Office> offices = new ArrayList<Office>();

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

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	@Override
	public String toString() {
		return "Organization [getFullName()=" + getFullName() + ", getInn()=" + getInn() + ", getKpp()=" + getKpp()
				+ ", getName()=" + getName() + ", getPhone()=" + getPhone() + ", getAddress()=" + getAddress()
				+ ", getIsActive()=" + getIsActive() + ", getId()=" + getId() + "]";
	}
}
