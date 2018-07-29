package com.ajax.restapiproject.doctype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.ajax.restapiproject.doctype.model.Doctype;

public class DoctypeDaoImpl implements DoctypeDao {

private final EntityManager em;
	
	@Autowired
	public DoctypeDaoImpl (EntityManager em) {
		this.em=em;
	}
	
	@Override
	public List<Doctype> getDoctypes() {
		TypedQuery<Doctype> query = em.createQuery("SELECT d FROM Doctype d", Doctype.class);
        return query.getResultList();
	}

	@Override
	public Doctype findByCode(String code) {
		TypedQuery<Doctype> query = em.createQuery("SELECT d FROM Doctype d WHERE d.code = :code", Doctype.class);
		Doctype doctype = query.getSingleResult();
		return doctype;
	}

	@Override
	public Doctype findById(Long id) {
		return em.find(Doctype.class, id);
	}

}
