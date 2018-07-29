package com.ajax.restapiproject.office.service;

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
import com.ajax.restapiproject.office.dao.OfficeDao;
import com.ajax.restapiproject.office.model.Office;
import com.ajax.restapiproject.office.view.OfficeIdViewResp;
import com.ajax.restapiproject.office.view.OfficeListViewReq;
import com.ajax.restapiproject.office.view.OfficeListViewResp;
import com.ajax.restapiproject.office.view.OfficeSaveViewReq;
import com.ajax.restapiproject.office.view.OfficeUpdateViewReq;
import com.ajax.restapiproject.organization.dao.OrganizationDao;
import com.ajax.restapiproject.organization.model.Organization;
import com.ajax.restapiproject.view.SuccessView;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

/**
 * Office service implementation
 * @author Al
 *
 */
@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final OfficeDao officeDao;
	
	private final OrganizationDao orgDao;
	
	private final OfficeValidation officeVal;
	
	@Autowired 
	public OfficeServiceImpl (OrganizationDao orgDao, OfficeDao officeDao, OfficeValidation officeVal) {
		this.orgDao = orgDao;
		this.officeDao = officeDao;
		this.officeVal = officeVal;
	}
	
	/* 
	 * Method for loading an organization by Id
	 */
	@Override
	public OfficeIdViewResp loadById(String id){
		
		logger.info("Request by id, loadById method: "+ id);
		
		String loadByIdValError = (officeVal.valId(id, true));
		
		if(!loadByIdValError.isEmpty()) {
			throw new BadRequestException(loadByIdValError);
		}
		
		Office office = officeDao.loadById(Long.parseLong(id));
		
		if(office == null) {
			throw new NotFoundException("Office");
		}
		
		OfficeIdViewResp viewResp = new OfficeIdViewResp(String.valueOf(office.getId()), office.getName(), 
				String.valueOf(office.getOrg().getId()), office.getAddress(), office.getPhone(), String.valueOf(office.getIsActive()));
		
		logger.info("Response for request by id, loadById method: "+ id + ". " + viewResp);
		
		return viewResp;
	}

	/* 
	 * Method for loading a list of organizations by name 
	 */
	@Override
	public List<OfficeListViewResp> loadByOrg(OfficeListViewReq reqView) {	
		
		logger.info("Request for filtering by organization, loadByOrgName method: " + reqView);
		
		Joiner joiner = Joiner.on(". ").skipNulls();
		
		String loadByOrgValErrors = joiner.join(
				Strings.emptyToNull(officeVal.valId(reqView.orgId, true)),
				Strings.emptyToNull(officeVal.valName(reqView.name, false)),
				Strings.emptyToNull(officeVal.valPhone(reqView.phone, false)),
				Strings.emptyToNull(officeVal.valIsActive(reqView.isActive, false))						
		);
		
		if(!loadByOrgValErrors.isEmpty()) {
			throw new BadRequestException(loadByOrgValErrors);
		}
		
		Organization org = orgDao.loadById(Long.parseLong(reqView.orgId));
		
		if(org == null) {
			throw new NotFoundException("Organization");
		}		
		
		Office office = new Office();
		
		office.setOrg(org);
		
		if (StringUtils.isNotBlank(reqView.name)) {
			office.setName(reqView.name);
		}
		
		if (StringUtils.isNotBlank(reqView.phone)) {
			office.setPhone(reqView.phone);
		}
		
		if (StringUtils.isNotBlank(reqView.isActive)) {
			office.setIsActive(Boolean.parseBoolean(reqView.isActive));
		}
		
		List<Office> officeList = officeDao.loadByOrg(office);
		
		List <OfficeListViewResp> viewResp = new ArrayList<OfficeListViewResp>();
		
		for (Office currentOffice : officeList) {
			viewResp.add(new OfficeListViewResp(String.valueOf(currentOffice.getId()), currentOffice.getName(), 
					String.valueOf(currentOffice.getIsActive())));
		}
		
		logger.info("Response for request for filtering by organization: " + reqView+ " is " + officeList);
		
		return viewResp;
	}

	/* 
	 * Method for saving provided organization
	 */
	@Override
	public SuccessView save(OfficeSaveViewReq reqView) {

		logger.info("Request for saving, save method: " + reqView);
		
		Joiner joiner = Joiner.on (". ").skipNulls();
		
		String saveValErrors = joiner.join(
				Strings.emptyToNull(officeVal.valId(reqView.orgId, true)),
				Strings.emptyToNull(officeVal.valName(reqView.name, true)),
				Strings.emptyToNull(officeVal.valAddress(reqView.address, true)),
				Strings.emptyToNull(officeVal.valPhone(reqView.phone, false)),
				Strings.emptyToNull(officeVal.valIsActive(reqView.isActive, false))						
		);
		
		if(!saveValErrors.isEmpty()) {
			throw new BadRequestException(saveValErrors);
		}
		
		Organization org = orgDao.loadById(Long.parseLong(reqView.orgId));
		
		if(org == null) {
			throw new NotFoundException("Organization");
		}	
		
		Office office = new Office();
		
		office.setName(reqView.name.trim());
		
		office.setOrg(org);
		
		if (StringUtils.isNotBlank(reqView.address)) {
			office.setAddress(reqView.address.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.phone)) {
			office.setPhone(reqView.phone.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.isActive)) {
			office.setIsActive(Boolean.parseBoolean(reqView.isActive.trim()));
		}
		
		logger.info("Office to be saved by save request, save method: " + reqView);
		
		officeDao.save(office);
		
		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}

	/* 
	 * Method for updating office by Id
	 */
	@Override
	public SuccessView update (OfficeUpdateViewReq reqView) {
		
		logger.info("Request for updating, update method: " + reqView);
		
		Joiner joiner = Joiner.on (". ").skipNulls();
		
		String updateValErrors = joiner.join(
				Strings.emptyToNull(officeVal.valId(reqView.id, true)),
				Strings.emptyToNull(officeVal.valName(reqView.name, true)),
				Strings.emptyToNull(officeVal.valAddress(reqView.address, true)),
				Strings.emptyToNull(officeVal.valPhone(reqView.phone, false)),
				Strings.emptyToNull(officeVal.valIsActive(reqView.isActive, false))						
		);
		
		if(!updateValErrors.isEmpty()) {
			throw new BadRequestException(updateValErrors);
		}
				
		Office office = officeDao.loadById(Long.parseLong(reqView.id));
		
		if(office==null) {
			throw new NotFoundException("Office");
		}
		
		logger.info("Office to be updated by update request, save method: " + reqView + ".  " + office);
		
		office.setName(reqView.name.trim());
		
		office.setAddress(reqView.address.trim());
	
		if (StringUtils.isNotBlank(reqView.phone)) {
			office.setPhone(reqView.phone.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.isActive)) {
			office.setIsActive(Boolean.parseBoolean(reqView.isActive.trim()));
		}
		
		logger.info("Office after update by update request, save method: " + reqView + ". " + office);
		
		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}
	
	/* 
	 * Method for deleting an organization by Id
	 */
	@Override
	public SuccessView deleteById(String id){
		
		logger.info("Request by id, deleteById method: "+ id);
		
		String deleteByIdValError = (officeVal.valId(id, true));
		
		if(!deleteByIdValError.isEmpty()) {
			throw new BadRequestException(deleteByIdValError);
		}
		
		Office office = officeDao.loadById(Long.parseLong(id));
				
		if(office==null) {
			throw new NotFoundException("Organization");
		}
		
		officeDao.delete(office);

		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}

}
