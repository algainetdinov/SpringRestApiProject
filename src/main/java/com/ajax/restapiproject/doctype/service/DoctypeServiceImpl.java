package com.ajax.restapiproject.doctype.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ajax.restapiproject.doctype.dao.DoctypeDao;
import com.ajax.restapiproject.doctype.model.Doctype;
import com.ajax.restapiproject.view.DictionaryView;

public class DoctypeServiceImpl implements DoctypeService {

	private DoctypeDao doctypeDao;
	
	@Autowired
	public DoctypeServiceImpl (DoctypeDao doctypeDao) {
		this.doctypeDao = doctypeDao;
	}
	
	@Override
	public Doctype findById(Long id) {
		return doctypeDao.findById(id);
	}

	@Override
	public Doctype findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
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
