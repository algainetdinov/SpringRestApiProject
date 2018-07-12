package com.ajax.restapiproject.office.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ajax.restapiproject.model.LegalEntity;
import com.ajax.restapiproject.organization.model.Organization;

/**
 * Office class
 */
@Entity
@Table(name = "office")
public class Office extends LegalEntity{
	
	/**
	 * Reference for a organization office belongs to
	 */
	@ManyToOne
	@JoinColumn(name = "org_id")
	private Organization org;
	
	/**
	 * Call superclass constructor in order to initialize isActive field
	 */
	public Office() {
		super();
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
}
