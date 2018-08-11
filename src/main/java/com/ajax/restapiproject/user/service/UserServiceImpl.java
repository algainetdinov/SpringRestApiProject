package com.ajax.restapiproject.user.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajax.restapiproject.country.dao.CountryDao;
import com.ajax.restapiproject.country.model.Country;
import com.ajax.restapiproject.doctype.dao.DoctypeDao;
import com.ajax.restapiproject.doctype.model.Doctype;
import com.ajax.restapiproject.document.model.Document;
import com.ajax.restapiproject.exception.BadRequestException;
import com.ajax.restapiproject.exception.NotFoundException;
import com.ajax.restapiproject.office.dao.OfficeDao;
import com.ajax.restapiproject.office.model.Office;
import com.ajax.restapiproject.user.dao.UserDao;
import com.ajax.restapiproject.user.model.User;
import com.ajax.restapiproject.user.model.UserMapper;
import com.ajax.restapiproject.user.view.UserIdViewResp;
import com.ajax.restapiproject.user.view.UserListViewReq;
import com.ajax.restapiproject.user.view.UserListViewResp;
import com.ajax.restapiproject.user.view.UserSaveViewReq;
import com.ajax.restapiproject.user.view.UserUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import ma.glasnost.orika.MappingContext;

/**
 * User service implementation
 * @author Al
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final UserDao userDao;
	
	private final OfficeDao officeDao;
	
	private final DoctypeDao doctypeDao;
	
	private final CountryDao countryDao;
		
	private final UserValidation userVal;
	
	private final UserMapper userMapper;
	
	private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	/**
	 * Constructor to set final fields
	 * @param userDao
	 * @param userVal
	 * @param doctypeDao
	 * @param officeDao
	 * @param countryDao
	 * @param userMapper
	 */
	@Autowired
	public UserServiceImpl(UserDao userDao, UserValidation userVal, DoctypeDao doctypeDao, OfficeDao officeDao, 
			CountryDao countryDao, UserMapper userMapper) {
		
		df.setLenient(false);
		this.userDao=userDao;
		this.userVal=userVal;
		this.doctypeDao=doctypeDao;
		this.officeDao=officeDao;
		this.countryDao=countryDao;
		this.userMapper=userMapper;
	}
	
	/**
	 * Retrieve user by Id
	 */
	@Override
	public UserIdViewResp loadById(String id) {

		logger.info("Request by id, loadById method: "+ id);
		
		String loadByIdValError = (userVal.valId(id, true));
		
		if(StringUtils.isNotBlank(loadByIdValError)) {
			throw new BadRequestException(loadByIdValError);
		}
		
		User user = userDao.findById(Long.parseLong(id));
		
		if(user == null) {
			throw new NotFoundException("User");
		}
		
		UserIdViewResp viewResp = new UserIdViewResp(String.valueOf(user.getId()), user.getFirstName(), user.getLastName(), 
				user.getMiddleName(), user.getPosition(), String.valueOf(user.getOffice().getId()), user.getPhone(), user.getDoc().getType().getCode(), 
				user.getDoc().getType().getName(), user.getDoc().getDocNumber(), DateFormatUtils.format(user.getDoc().getDocDate(), "dd.MM.yyyy"), 
				user.getCountry().getCode(), user.getCountry().getName(), String.valueOf(user.getIsIdentified()));
		
		logger.info("Response for request by id, loadById method: "+ id + ". " + viewResp);
		
		return viewResp;
	}
	
	/**
	 * Retrieve users by office
	 */
	@Override
	public List<UserListViewResp> loadByOffice(UserListViewReq reqView) {
		
		logger.info("Request for filtering by name, loadByName method: " + reqView);
		
		Joiner joiner = Joiner.on(". ").skipNulls();
		
		String loadByOrgValErrors = joiner.join(
				Strings.emptyToNull(userVal.valId(reqView.officeId, true)),
				Strings.emptyToNull(userVal.valFirstName(reqView.firstName, false)),
				Strings.emptyToNull(userVal.valLastName(reqView.lastName, false)),
				Strings.emptyToNull(userVal.valMiddleName(reqView.middleName, false)),
				Strings.emptyToNull(userVal.valPosition(reqView.position, false)),
				Strings.emptyToNull(userVal.valDocCode(reqView.docCode, false)),
				Strings.emptyToNull(userVal.valCitizCode(reqView.citizenshipCode, false))
		);
		
		if(StringUtils.isNotBlank(loadByOrgValErrors)) {
			throw new BadRequestException(loadByOrgValErrors);
		}
		
		Office office = officeDao.findById(Long.parseLong(reqView.officeId));
		
		if (office == null) {
			logger.error("loadByOffice method, office with id: "+ reqView.officeId + " is not found");
			throw new NotFoundException("Office");
		}
		
		MappingContext context = new MappingContext.Factory().getContext();
		
		context.setProperty("office", office);
				
		if (StringUtils.isNotBlank(reqView.docCode)) {
			
			Doctype doctype = doctypeDao.findByCode(reqView.docCode.trim());
			
			if (doctype == null) {
				throw new NotFoundException("Doctype");
			}
			
			else {
				Document doc = new Document();
				
				doc.setType(doctype);
				
				context.setProperty("doc", doc);
			}
		}
		
		if (StringUtils.isNotBlank(reqView.citizenshipCode)) {
			
			Country country = countryDao.findByCode(reqView.citizenshipCode.trim());
			
			if (country == null) {
				throw new NotFoundException("Country");
			}
			
			else {
				context.setProperty("country", country);
			}
		}
		
		User user = userMapper.map(reqView, User.class, context);
		
		logger.info("loadByOffice method, search of Office by User: "+ user);
		
		List<User> userList = userDao.findByOffice(user);
		
		logger.info("loadByOffice method, list of users: "+ userList);
		
		List <UserListViewResp> viewResp = new ArrayList<UserListViewResp>();
		
		for (User currentUser:userList) {
			
			viewResp.add(new UserListViewResp(String.valueOf(currentUser.getId()), currentUser.getFirstName(), 
					currentUser.getLastName(), currentUser.getMiddleName(), currentUser.getPosition()));
		}
		
		logger.info("Response for request for filtering by office: " + reqView+ " is " + userList);
		
		return viewResp;
	}

	/**
	 * Save user
	 */
	@Override
	public SuccessView save(UserSaveViewReq reqView) {
		
		logger.info("Request for saving: " + reqView);
		
		Joiner joiner = Joiner.on(". ").skipNulls();
		
		String saveValErrors = joiner.join(
				Strings.emptyToNull(userVal.valFirstName(reqView.firstName, true)),
				Strings.emptyToNull(userVal.valLastName(reqView.lastName, false)),
				Strings.emptyToNull(userVal.valMiddleName(reqView.middleName, false)),
				Strings.emptyToNull(userVal.valId(reqView.officeId, true)),
				Strings.emptyToNull(userVal.valPosition(reqView.position, true)),
				Strings.emptyToNull(userVal.valPhone(reqView.phone, false)),
				Strings.emptyToNull(userVal.valCitizCode(reqView.citizenshipCode, false)),
				Strings.emptyToNull(userVal.valIsIdentified(reqView.isIdentified, false))						
				);
		
		if (StringUtils.isNotBlank(reqView.docCode)||StringUtils.isNotBlank(reqView.docNumber)||StringUtils.isNotBlank(reqView.docDate)) {
			saveValErrors = joiner.join(
			Strings.emptyToNull(saveValErrors),
			Strings.emptyToNull(userVal.valDocCode(reqView.docCode, true)),
			Strings.emptyToNull(userVal.valDocNumber(reqView.docNumber, true)),
			Strings.emptyToNull(userVal.valDocDate(reqView.docDate, true))
			);
		}
		
		if(StringUtils.isNotBlank(saveValErrors)) {
			throw new BadRequestException(saveValErrors);
		}
		
		Office office = officeDao.findById(Long.parseLong(reqView.officeId));
		
		if (office == null) {
			throw new NotFoundException("Office");
		}
		
		User user = new User();
		
		user.setOffice(office);
		
		office.getUsers().add(user);
		
		if (StringUtils.isNotBlank(reqView.firstName)) {
			user.setFirstName(reqView.firstName.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.lastName)) {
			user.setLastName(reqView.lastName.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.middleName)) {
			user.setMiddleName(reqView.middleName.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.position)) {
			user.setPosition(reqView.position.trim());
		}	
		
		if (StringUtils.isNotBlank(reqView.phone)) {
			user.setPhone(reqView.phone.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.isIdentified)) {
			user.setIsIdentified(Boolean.parseBoolean(reqView.isIdentified.trim()));
		}
		
		if (StringUtils.isNotBlank(reqView.docCode)) {
			
			Doctype doctype = doctypeDao.findByCode(reqView.docCode.trim());
			
			if (doctype == null) {
				throw new NotFoundException("Doctype");
			}
			else {
				Document doc = new Document();
				doc.setType(doctype);
				try {
					doc.setDocDate(df.parse(reqView.docDate));
				} catch (ParseException e) {
					throw new BadRequestException("Cannot parse date");
				}
				doc.setDocNumber(reqView.docNumber.trim());
				user.setDoc(doc);
			}
		}
		
		if (StringUtils.isNotBlank(reqView.citizenshipCode)) {
			
			Country country = countryDao.findByCode(reqView.citizenshipCode.trim());
			
			if (country == null) {
				throw new NotFoundException("Country");
			}
			else {
				user.setCountry(country);
			}
		}
		
		userDao.save(user);
		
		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}

	/**
	 * Update user
	 */
	@Override
	public SuccessView update(UserUpdateViewReq reqView) {
		
		logger.info("Request for updating, update method: " + reqView);
		
		Joiner joiner = Joiner.on (". ").skipNulls();
		
		String updateValErrors = joiner.join(
				Strings.emptyToNull(userVal.valId(reqView.id, true)),
				Strings.emptyToNull(userVal.valFirstName(reqView.firstName, true)),
				Strings.emptyToNull(userVal.valLastName(reqView.lastName, false)),
				Strings.emptyToNull(userVal.valMiddleName(reqView.middleName, false)),
				Strings.emptyToNull(userVal.valPosition(reqView.position, true)),
				Strings.emptyToNull(userVal.valPhone(reqView.phone, false)),
				Strings.emptyToNull(userVal.valCitizCode(reqView.citizenshipCode, false)),
				Strings.emptyToNull(userVal.valIsIdentified(reqView.isIdentified, false))						
		);
		
		if (StringUtils.isNotBlank(reqView.docCode)||StringUtils.isNotBlank(reqView.docNumber)||StringUtils.isNotBlank(reqView.docDate)) {
		updateValErrors = joiner.join(
				Strings.emptyToNull(updateValErrors),
				Strings.emptyToNull(userVal.valDocCode(reqView.docCode, true)),
				Strings.emptyToNull(userVal.valDocNumber(reqView.docNumber, true)),
				Strings.emptyToNull(userVal.valDocDate(reqView.docDate, true))
				);
		}
		
		if(StringUtils.isNotBlank(updateValErrors)) {
			throw new BadRequestException(updateValErrors);
		}
				
		User user = userDao.findById(Long.parseLong(reqView.id));
		
		if(user == null) {
			throw new NotFoundException("User");
		}
		
		logger.info("User to be updated by update request, update method: " + reqView);
		
		user.setFirstName(reqView.firstName.trim());
		
		user.setPosition(reqView.position.trim());
	
		if (StringUtils.isNotBlank(reqView.lastName)) {
			user.setLastName(reqView.lastName.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.middleName)) {
			user.setMiddleName(reqView.middleName.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.isIdentified)) {
			user.setIsIdentified(Boolean.parseBoolean(reqView.isIdentified.trim()));
		}
		
		if (StringUtils.isNotBlank(reqView.phone)) {
			user.setPhone(reqView.phone.trim());
		}
		
		if (StringUtils.isNotBlank(reqView.docCode)) {
			Doctype doctype = doctypeDao.findByCode(reqView.docCode.trim());
			
			if (doctype == null) {
				throw new NotFoundException("Doctype");
			}
			
			Document currentDoc = user.getDoc();
						
			if (currentDoc != null) {
				logger.info(String.valueOf(currentDoc.getId()));
				user.setDoc(null);
			}
						
			Document doc = new Document();
			doc.setType(doctype);
			try {
				doc.setDocDate(df.parse(reqView.docDate));
			} catch (ParseException e) {
				throw new BadRequestException("Cannot parse date");
			}
			doc.setDocNumber(reqView.docNumber.trim());
			user.setDoc(doc);
		}
		
		if (StringUtils.isNotBlank(reqView.citizenshipCode)) {
			Country country = countryDao.findByCode(reqView.citizenshipCode.trim());
			
			if (country == null) {
				throw new NotFoundException("Country");
			}
			else {
				user.setCountry(country);
			}
		}
				
		logger.info("Organization after update by update request, update method: " + reqView);
		
		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	
	}

	/**
	 * Delete user
	 */
	@Override
	public SuccessView deleteById(String id) {
		
		logger.info("Request by id, deleteById method: "+ id);
		
		String deleteByIdValError = (userVal.valId(id, true));
		
		if(StringUtils.isNotBlank(deleteByIdValError)) {
			throw new BadRequestException(deleteByIdValError);
		}
		
		User user = userDao.findById(Long.parseLong(id));
				
		if(user == null) {
			throw new NotFoundException("User");
		}
		
		user.getOffice().getUsers().remove(user);
		
		user.setOffice(null);

		SuccessView viewResp = new SuccessView("success");
		
		return viewResp;
	}
}
