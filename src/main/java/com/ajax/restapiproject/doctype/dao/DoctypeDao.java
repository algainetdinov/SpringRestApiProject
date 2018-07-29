package com.ajax.restapiproject.doctype.dao;

import java.util.List;

import com.ajax.restapiproject.doctype.model.Doctype;

/**
 * Doctype DAO
 * @author Al
 *
 */
public interface DoctypeDao {
	/**
	 * Get list of countries
	 */
	List<Doctype> getDoctypes();
	
	/**
	 * Find country by code
	 */
	Doctype findByCode (String code);
	
	/**
	 * Find country by ID
	 */
	Doctype findById (Long id);	
}
