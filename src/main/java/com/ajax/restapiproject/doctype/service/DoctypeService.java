package com.ajax.restapiproject.doctype.service;

import java.util.List;

import com.ajax.restapiproject.doctype.model.Doctype;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Doctype service interface
 * @author Al
 *
 */
public interface DoctypeService {

	/**
	 * Find doctype by Id
	 * @param id
	 * @return
	 */
	Doctype loadById(Long id);
	
	/**
	 * Find doctype by code
	 * @param code
	 * @return
	 */
	Doctype loadByCode(String code);
	
	/**
	 * Retrieve all doctypes
	 * @return
	 */
	List<DictionaryView> loadAll();
}
