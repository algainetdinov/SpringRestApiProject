package com.ajax.restapiproject.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.MappingContext.Factory;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private OfficeDao officeDao;
	
	@Mock
	private DoctypeDao doctypeDao;
	
	@Mock
	private CountryDao countryDao;
	
	@Mock
	private UserValidation userVal; 
	
	@Mock
	private UserMapper userMapper;
	
	@Mock
	private Office office;
	
	@Mock
	private User user;
	
	@Mock
	private Document doc;
	
	@Mock 
	private Doctype type;
	
	@Mock
	private Country country;
		
	/**
	 * Check that user with id 15 exists
	 */
	@Test
	public void loadByIdSuccessTest() {	
		
		when(userDao.findById(anyLong())).thenReturn(user);
		when(userVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(office.getId()).thenReturn(1L);
		when(user.getOffice()).thenReturn(office);
		when(user.getCountry()).thenReturn(country);
		when(country.getName()).thenReturn("Ru");
		when(country.getCode()).thenReturn("000");
		when(user.getDoc()).thenReturn(doc);
		when(doc.getType()).thenReturn(type);
		when(type.getCode()).thenReturn("01");
		when(type.getName()).thenReturn("passport");
		when(doc.getDocNumber()).thenReturn("0101");
		when(doc.getDocDate()).thenReturn(new Date());		
		assertThat(userService.loadById("15")).isInstanceOf(UserIdViewResp.class);
	}	
	
	/**
	 * Check that user with id 3 do not exists
	 */
	@Test (expected = NotFoundException.class)
	public void loadByIdUserNotFoundTest() {
		
		when(userDao.findById(anyLong())).thenReturn(null);
		when(userVal.valId(anyString(), anyBoolean())).thenReturn(null);
		assertThat(userService.loadById("3")).isInstanceOf(NotFoundException.class);
	}
	
	/**
	 * Check that id is wrong
	 */
	@Test (expected = BadRequestException.class)
	public void loadByIdBadRequestTest() {		
		
		when(userVal.valId(anyString(), anyBoolean())).thenReturn("Wrong id");
		assertThat(userService.loadById("Z")).isInstanceOf(BadRequestException.class);		
	}
	
	/**
	 * Test for non-existing office
	 */
	@Test(expected = NotFoundException.class)
	public void loadByOfficeNotFoundTest () {
		
		UserListViewReq userListViewReq = mock(UserListViewReq.class);
		//Since I cannot use when(userListViewReq.officeId).thenReturn("1")
		userListViewReq.officeId="1";
		when(userVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(officeDao.findById(anyLong())).thenReturn(null);
		assertThat(userService.loadByOffice(userListViewReq)).isInstanceOf(NotFoundException.class);
		
	}
	
	/**
	 * Check for wrong Id
	 */
	@Test(expected = BadRequestException.class)
	public void loadByOfficeBadRequestTest () {
		
		UserListViewReq userListViewReq = mock(UserListViewReq.class);
		//Since I cannot use when(userListViewReq.officeId).thenReturn("1")
		userListViewReq.officeId="1";
		when(userVal.valId(anyString(), anyBoolean())).thenReturn("Wrong Id");
		assertThat(userService.loadByOffice(userListViewReq)).isInstanceOf(BadRequestException.class);
	}
	
	/**
	 * Check for successful loading by office
	 */
	@Test
	public void loadByOfficeSuccessTest () {
		
		UserListViewReq userListViewReq = mock(UserListViewReq.class);
		MappingContext mapContext = mock(MappingContext.class);
		Factory fact = mock(Factory.class);
		when(fact.getContext()).thenReturn(mapContext);
		when(userMapper.map(any(UserListViewReq.class), any(), any(MappingContext.class))).
				thenReturn(user);
		//Since I cannot use when(userListViewReq.officeId).thenReturn("1")
		userListViewReq.officeId = "1";
		when(userVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(userDao.findByOffice(any(User.class))).thenReturn(Arrays.asList(user));
		when(officeDao.findById(anyLong())).thenReturn(office);
		assertThat(userService.loadByOffice(userListViewReq)).hasAtLeastOneElementOfType(UserListViewResp.class);
	}
	
	/**
	 * Try to send request without required fields
	 */
	@Test(expected = BadRequestException.class)
	public void saveBadRequestTest () {
		
		UserSaveViewReq userSaveViewReq = mock (UserSaveViewReq.class);
		when(userVal.valFirstName(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(userService.save(userSaveViewReq)).isInstanceOf(BadRequestException.class);
	}
	
	/**
	 * Try to send request with non-existing office
	 */
	@Test(expected = NotFoundException.class)
	public void saveNotFoundTest () {
		
		UserSaveViewReq userSaveViewReq = mock (UserSaveViewReq.class);
		userSaveViewReq.officeId="1";
		when(officeDao.findById(anyLong())).thenReturn(null);
		assertThat(userService.save(userSaveViewReq)).isInstanceOf(NotFoundException.class);
	}
	
	/**
	 * Successful save
	 */
	@Test
	public void saveSuccessTest () {
		
		UserSaveViewReq userSaveViewReq = mock (UserSaveViewReq.class);
		userSaveViewReq.officeId="1";
		when(officeDao.findById(anyLong())).thenReturn(office);		
		assertThat(userService.save(userSaveViewReq)).isInstanceOf(SuccessView.class);
	}
	
	/**
	 * Check request with invalid Id
	 */
	@Test(expected = BadRequestException.class)
	public void updateBadRequestTest() {
		
		UserUpdateViewReq userUpdateViewReq = mock (UserUpdateViewReq.class);
		when(userVal.valId(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(userService.update(userUpdateViewReq)).isInstanceOf(BadRequestException.class);
	}
	
	/**
	 * Try to update non-existing user
	 */
	@Test(expected = NotFoundException.class)
	public void updateNotFoundTest() {
		
		UserUpdateViewReq userUpdateViewReq = mock (UserUpdateViewReq.class);
		userUpdateViewReq.id = "1";
		when(userDao.findById(anyLong())).thenReturn(null);
		assertThat(userService.update(userUpdateViewReq)).isInstanceOf(NotFoundException.class);
	}
	
	/**
	 * Successful update
	 */
	@Test
	public void updateSuccessTest() {
		
		UserUpdateViewReq userUpdateViewReq = mock (UserUpdateViewReq.class);
		userUpdateViewReq.id = "1";
		userUpdateViewReq.firstName = "John";
		userUpdateViewReq.position = "Manager";
		when(userDao.findById(anyLong())).thenReturn(user);
		assertThat(userService.update(userUpdateViewReq)).isInstanceOf(SuccessView.class);
	}
	
	/**
	 * Try to delete user with wrong Id
	 */
	@Test(expected = BadRequestException.class)
	public void deleteByIdBadRequestTest() {
		
		when(userVal.valId(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(userService.deleteById("1")).isInstanceOf(BadRequestException.class);
	}
	
	/**
	 * Try to delete non-existing user
	 */
	@Test(expected = NotFoundException.class)
	public void deleteByIdNotFoundTest() {
		
		when(userVal.valId(anyString(), anyBoolean())).thenReturn("");
		when(userDao.findById(anyLong())).thenReturn(null);
		assertThat(userService.deleteById("1")).isInstanceOf(NotFoundException.class);
	}
	
	/**
	 * Successful deletion
	 */
	@Test
	public void deleteByIdSuccessTest() {
	
		when(userVal.valId(anyString(), anyBoolean())).thenReturn("");
		when(userDao.findById(anyLong())).thenReturn(user);
		when(user.getOffice()).thenReturn(office);
		when(office.getUsers()).thenReturn(Arrays.asList(new User()));		
		assertThat(userService.deleteById("1")).isInstanceOf(SuccessView.class);
	}	

}
