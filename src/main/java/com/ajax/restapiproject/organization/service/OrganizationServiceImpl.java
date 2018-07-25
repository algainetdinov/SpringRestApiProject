/**
 * 
 */
package com.ajax.restapiproject.organization.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ajax.restapiproject.view.SuccessView;

/**
 * Organization service
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final OrganizationDao orgDao;
	
	private static final Pattern innPattern = Pattern.compile("\\d{10}");
	
	private static final Pattern kppPattern = Pattern.compile("\\d{9}");
	
	private static final Pattern phonePattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
	
	@Autowired 
	public OrganizationServiceImpl (OrganizationDao orgDao) {
		this.orgDao=orgDao;
	}
	
	/* 
	 * Method for loading an organization by Id
	 */
	@Override
	public OrganizationIdViewResp loadById(String id){
		
		logger.info("Request by id, loadById method: "+ id);
		
		boolean isBadReqExOcurred = false;
		
		String badReqExMessage="";
		
		if (StringUtils.isBlank(id)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization identifier should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		try {
			Long.parseLong(id);
		}
		
		catch (Exception e) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization identifier should be a number";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		if (Long.parseLong(id)<1) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization identifier should be a positive number";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		if(isBadReqExOcurred) {
			throw new BadRequestException(badReqExMessage);
		}
		
		Organization org = orgDao.loadById(Long.parseLong(id));
		
		if(org==null) {
			throw new NotFoundException("Organization is not found");
		}
		
		OrganizationIdViewResp viewResp = new OrganizationIdViewResp(org.getId(), org.getName(), org.getFullName(), 
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
		
		boolean isBadReqExOcurred = false;
		
		String badReqExMessage="";
		
		if (StringUtils.isBlank(reqView.name)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization name should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if (reqView.name.length()<5||reqView.name.length()>64) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization name length should be between 5 and 64 characters";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		if(StringUtils.isNotBlank(reqView.inn)) {
			if (!innPattern.matcher(reqView.inn).matches()) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization INN should be 10 digit number";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(StringUtils.isNotBlank(reqView.isActive)) {
			if ((!reqView.isActive.equalsIgnoreCase("true")) && (!reqView.isActive.equalsIgnoreCase("false"))) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization activity should be either true or false";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(isBadReqExOcurred) {
			throw new BadRequestException(badReqExMessage);
		}
		
		Organization org = new Organization();
		
		org.setName(reqView.name);
		
		if (StringUtils.isNotBlank(reqView.inn)) {
			org.setInn(reqView.inn);
		}
		
		if (StringUtils.isNotBlank(reqView.isActive)) {
			org.setIsActive(Boolean.parseBoolean(reqView.isActive));
		}
		
		List<Organization> orgList = orgDao.loadByName(org);
		
		List <OrganizationListViewResp> viewResp = new ArrayList<OrganizationListViewResp>();
		
		for (Organization currentOrg:orgList) {
			viewResp.add(new OrganizationListViewResp(currentOrg.getId(), currentOrg.getName(), 
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
		
		boolean isBadReqExOcurred = false;
		
		String badReqExMessage="";
		
		if (StringUtils.isBlank(reqView.name)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization name should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if (reqView.name.length()<5||reqView.name.length()>64) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization name length should be between 5 and 64 characters";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		

		if (StringUtils.isBlank(reqView.fullName)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization full name should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if (reqView.fullName.length()<7||reqView.fullName.length()>128) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization full name length should be between 7 and 128 characters";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		if(StringUtils.isBlank(reqView.inn)) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization INN should be provided";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if(StringUtils.isNotBlank(reqView.inn)) {
			if (!innPattern.matcher(reqView.inn).matches()) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization INN should be 10 digit number";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(StringUtils.isBlank(reqView.kpp)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization KPP should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
	
	
		if(StringUtils.isNotBlank(reqView.kpp)) {
			if (!kppPattern.matcher(reqView.kpp).matches()) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization KPP should be 9 digit number";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
	
		if (StringUtils.isBlank(reqView.address)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization address should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
	
	
		if (reqView.address.length()<5||reqView.address.length()>64) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization address length should be between 5 and 64 characters";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if(StringUtils.isNotBlank(reqView.isActive)) {
			if ((!reqView.isActive.equalsIgnoreCase("true")) && (!reqView.isActive.equalsIgnoreCase("false"))) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization activity should be either true or false";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(StringUtils.isNotBlank(reqView.phone)) {
			if (!phonePattern.matcher(reqView.phone).matches()) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization phone number should be in +7 xxx xx xx xx format";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(isBadReqExOcurred) {
			throw new BadRequestException(badReqExMessage);
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
		
		boolean isBadReqExOcurred = false;
		
		String badReqExMessage="";
		
		if (StringUtils.isBlank(reqView.id)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization identifier should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		try {
			Long.parseLong(reqView.id);
		}
		
		catch (Exception e) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization identifier should be a number";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		if (Long.parseLong(reqView.id)<1) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization identifier should be a positive number";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		if (StringUtils.isBlank(reqView.name)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization name should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if (reqView.name.length()<5||reqView.name.length()>64) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization name length should be between 5 and 64 characters";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		

		if (StringUtils.isBlank(reqView.fullName)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization full name should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if (reqView.fullName.length()<7||reqView.fullName.length()>128) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization full name length should be between 7 and 128 characters";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		if(StringUtils.isBlank(reqView.inn)) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization INN should be provided";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if(StringUtils.isNotBlank(reqView.inn)) {
			if (!innPattern.matcher(reqView.inn).matches()) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization INN should be 10 digit number";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(StringUtils.isBlank(reqView.kpp)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization KPP should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
	
	
		if(StringUtils.isNotBlank(reqView.kpp)) {
			if (!kppPattern.matcher(reqView.kpp).matches()) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization KPP should be 9 digit number";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
	
		if (StringUtils.isBlank(reqView.address)) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization address should be provided";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
	
	
		if (reqView.address.length()<5||reqView.address.length()>64) {
			isBadReqExOcurred=true;
			String currentExMessage="Organization address length should be between 5 and 64 characters";
			badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
		}
		
		
		if(StringUtils.isNotBlank(reqView.isActive)) {
			if ((!reqView.isActive.equalsIgnoreCase("true")) && (!reqView.isActive.equalsIgnoreCase("false"))) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization activity should be either true or false";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(StringUtils.isNotBlank(reqView.phone)) {
			if (!phonePattern.matcher(reqView.phone).matches()) {
				isBadReqExOcurred=true;
				String currentExMessage="Organization phone number should be in +7 xxx xx xx xx format";
				badReqExMessage=badReqExMessage.isEmpty()?currentExMessage:badReqExMessage+". "+currentExMessage;
			}
		}
		
		if(isBadReqExOcurred) {
			throw new BadRequestException(badReqExMessage);
		}
		
		Organization org = orgDao.loadById(Long.parseLong(reqView.id));
		
		if(org==null) {
			throw new NotFoundException("Organization is not found");
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

}
