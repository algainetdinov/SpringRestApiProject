package com.ajax.restapiproject.office.service;

import java.util.List;

import com.ajax.restapiproject.office.view.OfficeIdViewResp;
import com.ajax.restapiproject.office.view.OfficeListViewReq;
import com.ajax.restapiproject.office.view.OfficeListViewResp;
import com.ajax.restapiproject.office.view.OfficeSaveViewReq;
import com.ajax.restapiproject.office.view.OfficeUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * Service for Office entity
 * @author Al
 *
 */
public interface OfficeService {

	OfficeIdViewResp loadById(String id);
	
	List<OfficeListViewResp> loadByOrg(OfficeListViewReq reqView);
	
	SuccessView save (OfficeSaveViewReq reqView);
	
	SuccessView update (OfficeUpdateViewReq reqView);
	
	SuccessView deleteById (String id);
}
