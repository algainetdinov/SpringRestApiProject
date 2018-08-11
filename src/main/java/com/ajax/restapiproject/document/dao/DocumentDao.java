package com.ajax.restapiproject.document.dao;

import java.util.List;

import com.ajax.restapiproject.document.model.Document;

/**
 * Document DAO interface
 * @author Al
 *
 */
public interface DocumentDao {
	
	/**
	 * Get list of documents
	 */
	List<Document> findAll();
	
	/**
	 * Delete specified Document
	 * @param doc
	 */
	void delete(Document doc);
}
