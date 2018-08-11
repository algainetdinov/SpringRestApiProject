/**
 * 
 */
package com.ajax.restapiproject.organization.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajax.restapiproject.exception.BadRequestException;
import com.ajax.restapiproject.exception.NotFoundException;
import com.ajax.restapiproject.office.model.Office;
import com.ajax.restapiproject.organization.dao.OrganizationDao;
import com.ajax.restapiproject.organization.model.Organization;
import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

/**
 * Organization service
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final OrganizationDao orgDao;
	
	private final OrganizationValidation orgVal;
	
	/**
	 * Constructor to set final fields
	 * @param orgDao
	 * @param orgVal
	 */
	@Autowired 
	public OrganizationServiceImpl (OrganizationDao orgDao, OrganizationValidation orgVal) {
		
		this.orgDao = orgDao;
		
		this.orgVal = orgVal;
	}
	
	/* 
	 * Method for loading an organization by Id
	 */
	@Override
	public OrganizationIdViewResp loadById(String id){
		
		logger.info("Request by id, loadById method: "+ id);
		
		String loadByIdValError = (orgVal.valId(id, true));
		
		if(StringUtils.isNotBlank(loadByIdValError)) {
			throw new BadRequestException(loadByIdValError);
		}
		
		Organization org = orgDao.findById(Long.parseLong(id));
		
		if(org==null) {
			throw new NotFoundException("Organization");
		}
		
		OrganizationIdViewResp viewResp = new OrganizationIdViewResp(String.valueOf(org.getId()), org.getName(), org.getFullName(), 
				org.getInn(), org.getKpp(), org.getAddress(), org.getPhone(), String.valueOf(org.getIsActive()));
		
		logger.info("Response for request by id, loadById method: "+ id + ". " + viewResp);
		
		return viewResp;
	}

	/* 
	 * Method for loading a list of organizations by name 
	 */
	@Override
	public List<OrganizationListViewResp> loadByName(OrganizationListViewReq reqView) {	
		
		logger.info("Request for filtering by name, loadByName method: " + reqView);
		
		Joiner joiner = Joiner.on(". ").skipNulls();
		
		String loadByNameValErrors = joiner.join(
				Strings.emptyToNull(orgVal.valName(reqView.name, true)),
				Strings.emptyToNull(orgVal.valInn(reqView.inn, false)),
				Strings.emptyToNull(orgVal.valIsActive(reqView.isActive, false))						
		);
		
		if(StringUtils.isNotBlank(loadByNameValErrors)) {
			throw new BadRequestException(loadByNameValErrors);
		}
		
		Organization org = new Organization();
		
		org.setName(reqView.name);
		
		if (StringUtils.isNotBlank(reqView.inn)) {
			org.setInn(reqView.inn);
		}
		
		if (StringUtils.isNotBlank(reqView.isActive)) {
			org.setIsActive(Boolean.parseBoolean(reqView.isActive));
		}
		
		List<Organization> orgList = orgDao.findByName(org);
		
		if(orgList.isEmpty()) {
			throw new NotFoundException("Organization");
		}
		
		List <OrganizationListViewResp> viewResp = new ArrayList<OrganizationListViewResp>();
		
		for (Organization currentOrg:orgList) {
			viewResp.add(new OrganizationListViewResp(String.valueOf(currentOrg.getId()), currentOrg.getName(), 
					String.valueOf(currentOrg.getIsActive())));
		}
		
		logger.info("Response for request for filtering by name: " + reqView+ " is " + orgList);
		
		return viewResp;
	}

	/* 
	 * Method for saving provided organization
	 */
	@Override
	public SuccessView save(OrganizationSaveViewReq reqView) {

		logger.info("Request for saving, save method: " + reqView);
		
		Joiner joiner = Joiner.on (". ").skipNulls();
		
		String saveValErrors = joiner.join(
				Strings.emptyToNull(orgVal.valName(reqView.name, true)),
				Strings.emptyToNull(orgVal.valFullName(reqView.fullName, true)),
				Strings.emptyToNull(orgVal.valInn(reqView.inn, true)),
				Strings.emptyToNull(orgVal.valKpp(reqView.kpp, true)),
				Strings.emptyToNull(orgVal.valAddress(reqView.address, true)),
				Strings.emptyToNull(orgVal.valPhone(reqView.phone, false)),
				Strings.emptyToNull(orgVal.valIsActive(reqView.isActive, false))						
		);
		
		if(StringUtils.isNotBlank(saveValErrors)) {
			throw new BadRequestException(saveValErrors);
		}
		
		Organization org = new Organization();
		
		org.setName(reqView.name.trim());
		
		org.setFullName(reqView.fullName.trim());
		
		org.setInn(reqView.inn.trim());
		
		org.setKpp(reqView.kpp.trim());
		
		org.setAddress(reqView.address.trim());
		
		if (StringUtils.isNotBlank(reqView.phone)) {
			org.setPhone(reqView.phone.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.isActive)) {
			org.setIsActive(Boolean.parseBoolean(reqView.isActive.trim()));
		}
		
		logger.info("Organization to be saved by save request, save method: " + reqView);
		
		orgDao.save(org);
		
		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}

	/* 
	 * Method for updating organization by Id
	 */
	@Override
	public SuccessView update (OrganizationUpdateViewReq reqView) {
		
		logger.info("Request for updating, update method: " + reqView);
		
		Joiner joiner = Joiner.on (". ").skipNulls();
		
		String updateValErrors = joiner.join(
				Strings.emptyToNull(orgVal.valId(reqView.id, true)),
				Strings.emptyToNull(orgVal.valName(reqView.name, true)),
				Strings.emptyToNull(orgVal.valFullName(reqView.fullName, true)),
				Strings.emptyToNull(orgVal.valInn(reqView.inn, true)),
				Strings.emptyToNull(orgVal.valKpp(reqView.kpp, true)),
				Strings.emptyToNull(orgVal.valAddress(reqView.address, true)),
				Strings.emptyToNull(orgVal.valPhone(reqView.phone, false)),
				Strings.emptyToNull(orgVal.valIsActive(reqView.isActive, false))						
		);
		
		if(StringUtils.isNotBlank(updateValErrors)) {
			throw new BadRequestException(updateValErrors);
		}
				
		Organization org = orgDao.findById(Long.parseLong(reqView.id));
		
		if(org==null) {
			throw new NotFoundException("Organization");
		}
		
		logger.info("Organization to be updated by update request, save method: " + reqView + ".  " + org);
		
		org.setName(reqView.name.trim());
		
		org.setFullName(reqView.fullName.trim());
		
		org.setInn(reqView.inn.trim());
		
		org.setKpp(reqView.kpp.trim());
		
		org.setAddress(reqView.address.trim());
	
		if (StringUtils.isNotBlank(reqView.phone)) {
			org.setPhone(reqView.phone.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.isActive)) {
			org.setIsActive(Boolean.parseBoolean(reqView.isActive.trim()));
		}
		
		logger.info("Organization after update by update request, save method: " + reqView + ". "+ org);
		
		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}
	
	/* 
	 * Method for deleting an organization by Id
	 */
	@Override
	public SuccessView deleteById(String id){
		
		logger.info("Request by id, deleteById method: "+ id);
		
		String deleteByIdValError = orgVal.valId(id, true);
		
		if(StringUtils.isNotBlank(deleteByIdValError)) {
			throw new BadRequestException(deleteByIdValError);
		}
		
		Organization org = orgDao.findById(Long.parseLong(id));
				
		if (org==null) {
			throw new NotFoundException("Organization");
		}
		
		for (Office office: org.getOffices()) {
			office.setOrg(null);
		}
		
		orgDao.delete(org);

		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}
}
