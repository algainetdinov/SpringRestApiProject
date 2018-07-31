package com.ajax.restapiproject.document.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ajax.restapiproject.document.model.Document;

/**
 * Document DAO implementation
 * @author Al
 *
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final EntityManager em;
	
	public DocumentDaoImpl(EntityManager em) {
		this.em=em;
	}
	@Override
	public void delete(Document doc) {
		logger.info("Document to be removed: " + doc.toString());
		em.remove(doc);
	}

}
