package com.ajax.restapiproject.document.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ajax.restapiproject.document.model.Document;

/**
 * Document DAO implementation
 * 
 * @author Al
 *
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final EntityManager em;

	/**
	 * Constructor to set final fields
	 * 
	 * @param em
	 */
	public DocumentDaoImpl(EntityManager em) {

		this.em = em;
	}

	/**
	 * Implement logic for a getting list of countries through entity manager
	 */
	@Override
	public List<Document> findAll() {

		TypedQuery<Document> query = em.createQuery("SELECT d FROM Document d", Document.class);

		return query.getResultList();
	}

	/**
	 * Delete specified document
	 */
	@Override
	public void delete(Document doc) {

		logger.info("Document to be removed: " + doc.toString());

		em.remove(doc);
	}

}
