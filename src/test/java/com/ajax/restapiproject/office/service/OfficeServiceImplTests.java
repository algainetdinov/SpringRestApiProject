package com.ajax.restapiproject.office.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceImplTests {

	@InjectMocks
	private OfficeServiceImpl officeService;

	@Mock
	private OrganizationDao orgDao;

	@Mock
	private OfficeDao officeDao;

	@Mock
	private OfficeValidation officeVal;

	@Mock
	private Office office;

	@Mock
	private Organization org;

	/**
	 * Check that office with id 15 exists
	 */
	@Test
	public void loadByIdSuccessTest() {

		when(officeDao.findById(anyLong())).thenReturn(office);
		when(officeVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(office.getId()).thenReturn(1L);
		when(office.getName()).thenReturn("Ajax inc.");
		when(office.getAddress()).thenReturn("Moscow Lenina str");
		when(office.getPhone()).thenReturn("+7-999-666-66-66");
		when(office.getIsActive()).thenReturn(true);
		assertThat(officeService.loadById("15")).isInstanceOf(OfficeIdViewResp.class);
	}

	/**
	 * Check exception if office does not exist
	 */
	@Test(expected = NotFoundException.class)
	public void loadByIdNotFoundTest() {

		when(officeVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valName(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valPhone(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valIsActive(anyString(), anyBoolean())).thenReturn(null);
		when(officeDao.findById(anyLong())).thenReturn(null);
		assertThat(officeService.loadById("3")).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Check exception if request is incorrect
	 */
	@Test(expected = BadRequestException.class)
	public void loadByIdBadRequestTest() {

		when(officeVal.valId(anyString(), anyBoolean())).thenReturn("Wrong Id");
		assertThat(officeService.loadById("3")).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Check exception if Organization does not exist
	 */
	@Test(expected = NotFoundException.class)
	public void loadByOrgNotFoundTest() {

		OfficeListViewReq officeListViewReq = mock(OfficeListViewReq.class);
		officeListViewReq.orgId = "1";
		when(officeVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valName(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valPhone(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valIsActive(anyString(), anyBoolean())).thenReturn(null);
		when(orgDao.findById(anyLong())).thenReturn(null);
		assertThat(officeService.loadByOrg(officeListViewReq)).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Check that id is wrong
	 */
	@Test(expected = BadRequestException.class)
	public void loadByOrgBadRequestTest() {

		OfficeListViewReq officeListViewReq = mock(OfficeListViewReq.class);
		when(officeVal.valId(anyString(), anyBoolean())).thenReturn("Wrong id");
		assertThat(officeService.loadByOrg(officeListViewReq)).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Check for successful loading by organization
	 */
	@Test
	public void loadByOrgSuccessTest() {

		OfficeListViewReq officeListViewReq = mock(OfficeListViewReq.class);
		officeListViewReq.orgId = "1";
		when(officeVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valName(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valPhone(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valIsActive(anyString(), anyBoolean())).thenReturn(null);
		when(orgDao.findById(anyLong())).thenReturn(org);
		when(officeDao.findByOrg(Mockito.any(Office.class))).thenReturn(Arrays.asList(office));
		when(office.getId()).thenReturn(1L);
		when(office.getName()).thenReturn("Ajax inc.");
		when(office.getAddress()).thenReturn("Moscow Lenina str");
		when(office.getPhone()).thenReturn("+7-999-666-66-66");
		when(office.getIsActive()).thenReturn(true);
		assertThat(officeService.loadByOrg(officeListViewReq)).hasAtLeastOneElementOfType(OfficeListViewResp.class);
	}

	/**
	 * Try to send request without required fields
	 */
	@Test(expected = BadRequestException.class)
	public void saveBadRequestTest() {

		OfficeSaveViewReq officeSaveViewReq = mock(OfficeSaveViewReq.class);
		when(officeVal.valName(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(officeService.save(officeSaveViewReq)).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Try to send request with non-existing organization
	 */
	@Test(expected = NotFoundException.class)
	public void saveNotFoundTest() {

		OfficeSaveViewReq officeSaveViewReq = mock(OfficeSaveViewReq.class);
		officeSaveViewReq.orgId = "1";
		when(officeVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valName(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valPhone(anyString(), anyBoolean())).thenReturn(null);
		when(officeVal.valIsActive(anyString(), anyBoolean())).thenReturn(null);
		when(orgDao.findById(anyLong())).thenReturn(null);
		assertThat(officeService.save(officeSaveViewReq)).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Successful save
	 */
	@Test
	public void saveSuccessTest() {

		OfficeSaveViewReq officeSaveViewReq = mock(OfficeSaveViewReq.class);
		officeSaveViewReq.orgId = "1";
		officeSaveViewReq.name = "Test";
		when(orgDao.findById(anyLong())).thenReturn(org);
		assertThat(officeService.save(officeSaveViewReq)).isInstanceOf(SuccessView.class);
	}

	/**
	 * Check request with invalid Id
	 */
	@Test(expected = BadRequestException.class)
	public void updateBadRequestTest() {

		OfficeUpdateViewReq officeUpdateViewReq = mock(OfficeUpdateViewReq.class);
		officeUpdateViewReq.name = "Ajax";
		when(officeVal.valId(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(officeService.update(officeUpdateViewReq)).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Try to update non-existing office
	 */
	@Test(expected = NotFoundException.class)
	public void updateNotFoundTest() {

		OfficeUpdateViewReq officeUpdateViewReq = mock(OfficeUpdateViewReq.class);
		officeUpdateViewReq.id = "1";
		when(orgDao.findById(anyLong())).thenReturn(null);
		assertThat(officeService.update(officeUpdateViewReq)).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Successful update
	 */
	@Test
	public void updateSuccessTest() {

		OfficeUpdateViewReq officeUpdateViewReq = mock(OfficeUpdateViewReq.class);
		officeUpdateViewReq.id = "1";
		when(officeDao.findById(anyLong())).thenReturn(office);
		assertThat(officeService.update(officeUpdateViewReq)).isInstanceOf(SuccessView.class);
	}

	/**
	 * Try to delete office with wrong Id
	 */
	@Test(expected = BadRequestException.class)
	public void deleteByIdBadRequestTest() {

		when(officeVal.valId(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(officeService.deleteById("1")).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Try to delete non-existing office
	 */
	@Test(expected = NotFoundException.class)
	public void deleteByIdNotFoundTest() {

		when(officeVal.valId(anyString(), anyBoolean())).thenReturn("");
		when(officeDao.findById(anyLong())).thenReturn(null);
		assertThat(officeService.deleteById("1")).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Successful deletion
	 */
	@Test
	public void deleteByIdSuccessTest() {

		when(officeVal.valId(anyString(), anyBoolean())).thenReturn("");
		when(officeDao.findById(anyLong())).thenReturn(office);
		when(office.getOrg()).thenReturn(org);
		when(org.getOffices()).thenReturn(Arrays.asList(new Office()));
		assertThat(officeService.deleteById("1")).isInstanceOf(SuccessView.class);
	}

}
