package com.ajax.restapiproject.document.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ajax.restapiproject.doctype.model.DocType;
import com.ajax.restapiproject.model.BaseEntity;

/**
 * Document class
 */
@Entity
@Table(name = "document")
public class Document extends BaseEntity{
	
	/**
	 * Date of issuing document
	 */
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date docDate;
	
	/**
	 * Date of issuing document
	 */
	@Column(name = "number", length = 32, nullable = true)
	private String docNumber;
	
	/**
	 * Date of issuing a document
	 */
	@ManyToOne
	@JoinColumn(name = "type_code")
	private DocType type;

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

	public DocType getType() {
		return type;
	}

	public void setType(DocType type) {
		this.type = type;
	}
}
