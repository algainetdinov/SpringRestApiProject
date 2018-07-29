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

	Doctype findById(Long id);
	
	Doctype findByCode(String code);
	
	List<DictionaryView> findAll();
}
