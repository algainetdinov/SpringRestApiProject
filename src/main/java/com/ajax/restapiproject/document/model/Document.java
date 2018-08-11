package com.ajax.restapiproject.document.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ajax.restapiproject.doctype.model.Doctype;
import com.ajax.restapiproject.model.BaseEntity;

/**
 * Document entity
 */
@Entity
@Table(name = "document")
public class Document extends BaseEntity{
	
	/**
	 * Date of issuing a document
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date docDate;
	
	/**
	 * Number of a document
	 */
	@Column(name = "number", length = 32, nullable = true)
	private String docNumber;
	
	/**
	 * Type of a document
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_code")
	private Doctype type;

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public Doctype getType() {
		return type;
	}

	public void setType(Doctype type) {
		this.type = type;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Document [getDocDate()=" + getDocDate() + ", getDocNumber()=" + getDocNumber() + ", getType()="
				+ getType() + ", getId()=" + getId() + "]";
	}
}
