package com.ajax.restapiproject.organization.service;

import java.util.List;

import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;

public interface OrganizationService {
	
	OrganizationIdViewResp loadById(String id);
	
	List<OrganizationListViewResp> loadByName(OrganizationListViewReq reqView);
	
	void save (OrganizationSaveViewReq reqView);
	
	void update (OrganizationUpdateViewReq reqView);
}
