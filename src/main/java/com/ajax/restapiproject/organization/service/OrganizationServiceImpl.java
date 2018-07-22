/**
 * 
 */
package com.ajax.restapiproject.organization.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajax.restapiproject.exception.BadRequestException;
import com.ajax.restapiproject.exception.NotFoundException;
import com.ajax.restapiproject.organization.dao.OrganizationDao;
import com.ajax.restapiproject.organization.model.Organization;
import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;

/**
 * @author Al
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	private final OrganizationDao orgDao;
	
	@Autowired 
	public OrganizationServiceImpl (OrganizationDao orgDao) {
		this.orgDao=orgDao;
	}
	
	/* (non-Javadoc)
	 * @see com.ajax.restapiproject.organization.service.OrganizationService#loadById(java.lang.Long)
	 */
	@Override
	public OrganizationIdViewResp loadById(String id){
		if (id.trim().isEmpty()) {
			throw new BadRequestException("Organization identifier should be provided");
		}
		
		try {
			Long.parseLong(id);
		}
		catch (Exception e) {
			throw new BadRequestException("Organization identifier should be a number");
		}
		
		if (Long.parseLong(id)<1) {
			throw new BadRequestException("Organization identifier should be positive number");
		}
		
		Organization org = orgDao.loadById(Long.parseLong(id));
		
		if(org==null) {
			throw new NotFoundException("Organization is not found");
		}
		
		OrganizationIdViewResp respView = new OrganizationIdViewResp(org.getId(), org.getName(), org.getFullName(), 
				org.getInn(), org.getKpp(), org.getAddress(), org.getPhone(), String.valueOf(org.getIsActive()));
		
		return respView;
	}

	/* (non-Javadoc)
	 * @see com.ajax.restapiproject.organization.service.OrganizationService#loadByName(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public List<OrganizationListViewResp> loadByName(OrganizationListViewReq reqView) {
		
		if (reqView.name.trim().isEmpty()) {
			throw new BadRequestException("Organization name should be provided");
		}
		
		if (reqView.name.trim().length()<5||reqView.name.trim().length()>64) {
			throw new BadRequestException("Organization name length should be between 5 and 64 characters");
		}
		
		if(reqView.inn.trim()!="") {
			try {
				Integer.parseInt(reqView.inn);
				}
			catch (Exception e) {
				throw new BadRequestException("Organization INN length should be numerical");
			}
		
			if (reqView.inn.trim().length()!=10) {
				throw new BadRequestException("Organization INN length should be 10 nummerical characters");
			}
		}
		
		if(reqView.isActive.trim()!="") {
			if (reqView.isActive.trim().toLowerCase() != "true" && reqView.isActive.trim().toLowerCase() != "false") {
				throw new BadRequestException("Organization activity should be either true or false");
			}
		}
		
		Organization org = new Organization();
		org.setName(reqView.name.trim());
		if(reqView.inn.trim()!="") {
			org.setInn(reqView.inn);
		}
		if(reqView.isActive.trim()!="") {
		org.setIsActive(Boolean.parseBoolean(reqView.isActive));
		}
		List<Organization> orgList = orgDao.loadByName(org);
		List <OrganizationListViewResp> viewResp = new ArrayList<OrganizationListViewResp>();
		for (Organization currentOrg:orgList) {
			viewResp.add(new OrganizationListViewResp(currentOrg.getId(), currentOrg.getName(), 
					String.valueOf(currentOrg.getIsActive())));
		}
		
		return viewResp;
	}

	/* (non-Javadoc)
	 * @see com.ajax.restapiproject.organization.service.OrganizationService#save(com.ajax.restapiproject.organization.model.Organization)
	 */
	@Override
	public void save(OrganizationSaveViewReq reqView) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.ajax.restapiproject.organization.service.OrganizationService#update(com.ajax.restapiproject.organization.model.Organization)
	 */
	@Override
	public void update(OrganizationUpdateViewReq reqView) {
		// TODO Auto-generated method stub

	}

}
