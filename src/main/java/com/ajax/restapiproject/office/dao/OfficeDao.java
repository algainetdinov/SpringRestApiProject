package com.ajax.restapiproject.office.dao;

import java.util.List;

import com.ajax.restapiproject.office.model.Office;

public interface OfficeDao {
	
	/**
	 * Get office by Id
	 * @param id
	 * @return
	 */
	Office loadById(Long id);
	
	/**
	 * Load by name
	 * @param org
	 * @return
	 */
	List <Office> loadByOrg(Office office);
	
	/**
	 * Save office
	 * @param org
	 */
	void save(Office office);
	
	/**
	 * Delete an office by Id
	 * @param org
	 */
	void delete(Office off);

}
