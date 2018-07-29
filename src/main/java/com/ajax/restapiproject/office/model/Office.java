package com.ajax.restapiproject.office.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ajax.restapiproject.model.LegalEntity;
import com.ajax.restapiproject.organization.model.Organization;
import com.ajax.restapiproject.user.model.User;

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
	
	@OneToMany(mappedBy="office", cascade = CascadeType.ALL)
	private List<User> users;
	
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Office [getOrg()=" + getOrg().getId() + ", getUsers()=" + getUsers() + ", getName()=" + getName()
				+ ", getPhone()=" + getPhone() + ", getAddress()=" + getAddress() + ", getIsActive()=" + getIsActive()
				+ ", getId()=" + getId() + "]";
	}
}
