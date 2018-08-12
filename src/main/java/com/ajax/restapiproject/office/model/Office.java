package com.ajax.restapiproject.office.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ajax.restapiproject.model.LegalEntity;
import com.ajax.restapiproject.organization.model.Organization;
import com.ajax.restapiproject.user.model.User;

/**
 * Office entity
 */
@Entity
@Table(name = "office")
public class Office extends LegalEntity{
	
	/**
	 * Reference for a organization office belongs to
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Organization org;
	
	@OneToMany(mappedBy="office", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<User>();
	
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

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Office [getOrg()=" + getOrg() + ", getName()=" + getName()
				+ ", getPhone()=" + getPhone() + ", getAddress()=" + getAddress() + ", getIsActive()=" + getIsActive()
				+ ", getId()=" + getId() + "]";
	}
}
