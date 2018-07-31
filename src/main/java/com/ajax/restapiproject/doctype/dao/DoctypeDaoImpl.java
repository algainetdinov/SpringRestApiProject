package com.ajax.restapiproject.doctype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajax.restapiproject.doctype.model.Doctype;

@Repository
/**
 * Doctype DAO implementation
 * @author Al
 *
 */
public class DoctypeDaoImpl implements DoctypeDao {

	private final EntityManager em;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
	public Doctype loadByCode(String code) {
		logger.info("loadByCode method called with code: "+code);
		TypedQuery<Doctype> query = em.createQuery("SELECT d FROM Doctype d WHERE d.code = :code", Doctype.class).setParameter("code", code);
		Doctype doctype = query.getResultList().stream().findFirst().orElse(null);
		return doctype;
	}

	@Override
	public Doctype loadById(Long id) {
		return em.find(Doctype.class, id);
	}

}
