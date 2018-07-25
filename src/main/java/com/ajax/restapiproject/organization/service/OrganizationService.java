package com.ajax.restapiproject.organization.service;

import java.util.List;

import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

public interface OrganizationService {
	
	OrganizationIdViewResp loadById(String id);
	
	List<OrganizationListViewResp> loadByName(OrganizationListViewReq reqView);
	
	SuccessView save (OrganizationSaveViewReq reqView);
	
	SuccessView update (OrganizationUpdateViewReq reqView);
}
