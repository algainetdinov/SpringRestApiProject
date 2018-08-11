package com.ajax.restapiproject.organization.dao;

import java.util.List;

import com.ajax.restapiproject.organization.model.Organization;

/**
 * DAO for retrieving organization
 */
public interface OrganizationDao {
	
	/**
	 * Get organization by Id
	 * @param id
	 * @return
	 */
	Organization findById(Long id);
	
	/**
	 * Load list of organization
	 * @return
	 */
	List<Organization> findAll();
	
	/**
	 * Load by name
	 * @param org
	 * @return
	 */
	List <Organization> findByName(Organization org);
	
	/**
	 * Save organization
	 * @param org
	 */
	void save(Organization org);
	
	/**
	 * Delete an organization by Id
	 * @param org
	 */
	void delete(Organization org);

}
