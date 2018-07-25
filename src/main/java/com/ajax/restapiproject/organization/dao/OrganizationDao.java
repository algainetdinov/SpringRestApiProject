package com.ajax.restapiproject.organization.dao;

import java.util.List;

import com.ajax.restapiproject.organization.model.Organization;

/**
 * Dao for retrieving organization
 */
public interface OrganizationDao {
	
	/**
	 * Get organization by Id
	 * @param id
	 * @return
	 */
	Organization loadById(Long id);
	
	/**
	 * Load by name
	 * @param org
	 * @return
	 */
	List <Organization> loadByName(Organization org);
	
	/**
	 * Save organization
	 * @param org
	 */
	void save(Organization org);
}
