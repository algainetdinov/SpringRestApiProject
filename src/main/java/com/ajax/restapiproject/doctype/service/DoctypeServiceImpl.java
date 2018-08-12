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
 * 
 * @author Al
 *
 */
@Service
public class DoctypeServiceImpl implements DoctypeService {

	private DoctypeDao doctypeDao;

	@Autowired
	public DoctypeServiceImpl(DoctypeDao doctypeDao) {

		this.doctypeDao = doctypeDao;
	}

	/**
	 * Find doctype by Id
	 */
	@Override
	public Doctype loadById(Long id) {

		return doctypeDao.findById(id);
	}

	/**
	 * Find doctype by code
	 */
	@Override
	public Doctype loadByCode(String code) {

		return doctypeDao.findByCode(code);
	}

	/**
	 * Retrieve all doctypes
	 */
	@Override
	public List<DictionaryView> loadAll() {

		List<Doctype> doctypeList = doctypeDao.findAll();

		List<DictionaryView> dicView = new ArrayList<>();

		for (Doctype doctype : doctypeList) {

			dicView.add(new DictionaryView(doctype.getCode(), doctype.getName()));

		}

		return dicView;
	}

}
