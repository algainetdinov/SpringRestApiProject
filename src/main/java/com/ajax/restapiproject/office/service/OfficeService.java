package com.ajax.restapiproject.office.service;

import java.util.List;

import com.ajax.restapiproject.office.view.OfficeIdViewResp;
import com.ajax.restapiproject.office.view.OfficeListViewReq;
import com.ajax.restapiproject.office.view.OfficeListViewResp;
import com.ajax.restapiproject.office.view.OfficeSaveViewReq;
import com.ajax.restapiproject.office.view.OfficeUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * Service interface for Office entity
 * 
 * @author Al
 *
 */
public interface OfficeService {

	/**
	 * Load an office by Id
	 * 
	 * @param id
	 * @return
	 */
	OfficeIdViewResp loadById(String id);

	/**
	 * Load an office by organization
	 * 
	 * @param reqView
	 * @return
	 */
	List<OfficeListViewResp> loadByOrg(OfficeListViewReq reqView);

	/**
	 * Save an office
	 * 
	 * @param reqView
	 * @return
	 */
	SuccessView save(OfficeSaveViewReq reqView);

	/**
	 * Update an office
	 * 
	 * @param reqView
	 * @return
	 */
	SuccessView update(OfficeUpdateViewReq reqView);

	/**
	 * Delete an office
	 * 
	 * @param id
	 * @return
	 */
	SuccessView deleteById(String id);
}
