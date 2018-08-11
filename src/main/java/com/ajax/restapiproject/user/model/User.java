package com.ajax.restapiproject.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ajax.restapiproject.country.model.Country;
import com.ajax.restapiproject.document.model.Document;
import com.ajax.restapiproject.model.Person;
import com.ajax.restapiproject.office.model.Office;

/**
 * User entity
 */
@Entity
@Table(name = "user")
public class User extends Person{
	
	/**
	 * Flag about person identification
	 */
	@Column(name = "is_identified")
	private Boolean isIdentified;
	
	/**
	 * Person's position in organization
	 */
	@Column(name = "position", length = 32, nullable = false)
	private String position;
	
	/**
	 * Person's position in organization
	 */
	@Column(name = "phone", length = 18)
	private String phone;
	
	/**
	 * Person's office
	 */
	@ManyToOne
	@JoinColumn(name="office_id") 
	private Office office;
	
	/**
	 * Person's document
	 */
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name="doc_id") 
	private Document doc;
	
	/**
	 * Person's citizenship
	 */
	@ManyToOne
	@JoinColumn(name = "citiz_id")
	private Country country;
	
	public User() {
		super();
	}
	
	public User(Long id) {
		this.setId(id);
	}

	public Boolean getIsIdentified() {
		return isIdentified;
	}

	public void setIsIdentified(Boolean isIdentified) {
		this.isIdentified = isIdentified;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "User [getIsIdentified()=" + getIsIdentified() + ", getPosition()=" + getPosition() + ", getPhone()="
				+ getPhone() + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getMiddleName()=" + getMiddleName() + ", getId()=" + getId() + "]";
	}
}
