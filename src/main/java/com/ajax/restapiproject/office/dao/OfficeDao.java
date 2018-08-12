package com.ajax.restapiproject.office.dao;

import java.util.List;

import com.ajax.restapiproject.office.model.Office;

/**
 * Office DAO interface
 * 
 * @author Al
 *
 */
public interface OfficeDao {

	/**
	 * Find office by Id
	 * 
	 * @param id
	 * @return
	 */
	Office findById(Long id);

	/**
	 * Find office by organization
	 * 
	 * @param org
	 * @return
	 */
	List<Office> findByOrg(Office office);

	/**
	 * Save office
	 * 
	 * @param org
	 */
	void save(Office office);

	/**
	 * Delete an office by Id
	 * 
	 * @param org
	 */
	void delete(Office office);

	/**
	 * Retrieve list of offices
	 * 
	 * @return
	 */
	List<Office> findAll();
}
