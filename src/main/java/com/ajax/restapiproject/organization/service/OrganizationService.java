package com.ajax.restapiproject.organization.service;

import java.util.List;

import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * Organizaton service interface
 * 
 * @author Al
 *
 */
public interface OrganizationService {

	/**
	 * Load an organization by Id
	 * 
	 * @param id
	 * @return
	 */
	OrganizationIdViewResp loadById(String id);

	/**
	 * Load an organization by name
	 * 
	 * @param reqView
	 * @return
	 */
	List<OrganizationListViewResp> loadByName(OrganizationListViewReq reqView);

	/**
	 * Save an organization
	 * 
	 * @param reqView
	 * @return
	 */
	SuccessView save(OrganizationSaveViewReq reqView);

	/**
	 * Update an organization
	 * 
	 * @param reqView
	 * @return
	 */
	SuccessView update(OrganizationUpdateViewReq reqView);

	/**
	 * Delete an organization
	 * 
	 * @param id
	 * @return
	 */
	SuccessView deleteById(String id);
}
