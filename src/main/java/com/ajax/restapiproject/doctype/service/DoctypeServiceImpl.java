package com.ajax.restapiproject.doctype.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajax.restapiproject.doctype.dao.DoctypeDao;
import com.ajax.restapiproject.doctype.model.Doctype;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Doctype service implementation
 * @author Al
 *
 */
@Service
public class DoctypeServiceImpl implements DoctypeService {

	private DoctypeDao doctypeDao;
	
	@Autowired
	public DoctypeServiceImpl (DoctypeDao doctypeDao) {
		this.doctypeDao = doctypeDao;
	}
	
	@Override
	public Doctype findById(Long id) {
		return doctypeDao.loadById(id);
	}

	@Override
	public Doctype findByCode(String code) {
		return doctypeDao.loadByCode(code);
	}

	@Override
	public List<DictionaryView> findAll() {
		
		List<Doctype> doctypeList = doctypeDao.getDoctypes();
		
		List<DictionaryView> dicView = new ArrayList<>();
		
		for (Doctype doctype : doctypeList) {
			
			dicView.add(new DictionaryView(doctype.getCode(), doctype.getName()));
		
		}
		return dicView;
	}

}
