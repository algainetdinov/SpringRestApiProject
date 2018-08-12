package com.ajax.restapiproject.organization.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

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
import com.ajax.restapiproject.organization.dao.OrganizationDao;
import com.ajax.restapiproject.organization.model.Organization;
import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * Test Organization service implementation
 * 
 * @author Al
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceImplTests {

	@InjectMocks
	private OrganizationServiceImpl orgService;

	@Mock
	private OrganizationDao orgDao;

	@Mock
	private OfficeDao officeDao;

	@Mock
	private OrganizationValidation orgVal;

	@Mock
	private Office office;

	@Mock
	private Organization org;

	/**
	 * Check that organization with id 15 exists
	 */
	@Test
	public void loadByIdSuccessTest() {

		when(orgDao.findById(anyLong())).thenReturn(org);
		when(orgVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(org.getId()).thenReturn(1L);
		when(org.getName()).thenReturn("Ajax inc.");
		when(org.getFullName()).thenReturn("Ajax inc.");
		when(org.getInn()).thenReturn("Ajax inc.");
		when(org.getKpp()).thenReturn("Ajax inc.");
		when(org.getAddress()).thenReturn("Moscow Lenina str");
		when(org.getPhone()).thenReturn("+7-999-666-66-66");
		when(org.getIsActive()).thenReturn(true);

		assertThat(orgService.loadById("15")).isInstanceOf(OrganizationIdViewResp.class);
	}

	/**
	 * Check exception if organization does not exist
	 */
	@Test(expected = NotFoundException.class)
	public void loadByIdNotFoundTest() {

		when(orgVal.valId(anyString(), anyBoolean())).thenReturn(null);
		when(officeDao.findById(anyLong())).thenReturn(null);
		assertThat(orgService.loadById("3")).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Check exception if request is incorrect
	 */
	@Test(expected = BadRequestException.class)
	public void loadByIdBadRequestTest() {

		when(orgVal.valId(anyString(), anyBoolean())).thenReturn("Wrong Id");
		assertThat(orgService.loadById("3")).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Check exception if organization does not exist
	 */
	@Test(expected = NotFoundException.class)
	public void loadByNameNotFoundTest() {

		OrganizationListViewReq organizationListViewReq = mock(OrganizationListViewReq.class);
		organizationListViewReq.name = "Ajax";
		when(orgVal.valName(anyString(), anyBoolean())).thenReturn(null);
		when(orgDao.findByName(Mockito.any(Organization.class))).thenReturn(Collections.emptyList());
		assertThat(orgService.loadByName(organizationListViewReq)).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Check that id is wrong
	 */
	@Test(expected = BadRequestException.class)
	public void loadByNameBadRequestTest() {

		OrganizationListViewReq organizationListViewReq = mock(OrganizationListViewReq.class);
		when(orgVal.valName(anyString(), anyBoolean())).thenReturn("Wrong name");
		assertThat(orgService.loadByName(organizationListViewReq)).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Check for successful loading by organization name
	 */
	@Test
	public void loadByNameSuccessTest() {

		OrganizationListViewReq organizationListViewReq = mock(OrganizationListViewReq.class);
		organizationListViewReq.name = "Ajax";
		when(orgVal.valName(anyString(), anyBoolean())).thenReturn(null);
		when(orgDao.findByName(Mockito.any(Organization.class))).thenReturn(Arrays.asList(org));
		when(org.getId()).thenReturn(1L);
		when(org.getName()).thenReturn("Ajax inc.");
		when(org.getIsActive()).thenReturn(true);
		assertThat(orgService.loadByName(organizationListViewReq))
				.hasAtLeastOneElementOfType(OrganizationListViewResp.class);
	}

	/**
	 * Try to send request without required fields
	 */
	@Test(expected = BadRequestException.class)
	public void saveBadRequestTest() {

		OrganizationSaveViewReq organizationSaveViewReq = mock(OrganizationSaveViewReq.class);
		when(orgVal.valName(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(orgService.save(organizationSaveViewReq)).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Successful save
	 */
	@Test
	public void saveSuccessTest() {

		OrganizationSaveViewReq organizationSaveViewReq = mock(OrganizationSaveViewReq.class);
		organizationSaveViewReq.name = "Ajax";
		organizationSaveViewReq.fullName = "Ajax inc.";
		organizationSaveViewReq.inn = "1111111111";
		organizationSaveViewReq.kpp = "22222222";
		organizationSaveViewReq.address = "Moscow Lenina str";
		assertThat(orgService.save(organizationSaveViewReq)).isInstanceOf(SuccessView.class);
	}

	/**
	 * Check request with invalid Id
	 */
	@Test(expected = BadRequestException.class)
	public void updateBadRequestTest() {

		OrganizationUpdateViewReq organizationUpdateViewReq = mock(OrganizationUpdateViewReq.class);
		when(orgVal.valId(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(orgService.update(organizationUpdateViewReq)).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Try to update non-existing organization
	 */
	@Test(expected = NotFoundException.class)
	public void updateNotFoundTest() {

		OrganizationUpdateViewReq organizationUpdateViewReq = mock(OrganizationUpdateViewReq.class);
		organizationUpdateViewReq.id = "1";
		when(orgDao.findById(anyLong())).thenReturn(null);
		assertThat(orgService.update(organizationUpdateViewReq)).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Successful update
	 */
	@Test
	public void updateSuccessTest() {

		OrganizationUpdateViewReq organizationUpdateViewReq = mock(OrganizationUpdateViewReq.class);
		organizationUpdateViewReq.id = "1";
		organizationUpdateViewReq.name = "Ajax";
		organizationUpdateViewReq.fullName = "Ajax inc.";
		organizationUpdateViewReq.inn = "1111111111";
		organizationUpdateViewReq.kpp = "22222222";
		organizationUpdateViewReq.address = "Moscow Lenina str";
		when(orgDao.findById(anyLong())).thenReturn(org);
		assertThat(orgService.update(organizationUpdateViewReq)).isInstanceOf(SuccessView.class);
	}

	/**
	 * Try to delete organization with wrong Id
	 */
	@Test(expected = BadRequestException.class)
	public void deleteByIdBadRequestTest() {

		when(orgVal.valId(anyString(), anyBoolean())).thenReturn("Error");
		assertThat(orgService.deleteById("1")).isInstanceOf(BadRequestException.class);
	}

	/**
	 * Try to delete non-existing organization
	 */
	@Test(expected = NotFoundException.class)
	public void deleteByIdNotFoundTest() {

		when(orgVal.valId(anyString(), anyBoolean())).thenReturn("");
		when(orgDao.findById(anyLong())).thenReturn(null);
		assertThat(orgService.deleteById("1")).isInstanceOf(NotFoundException.class);
	}

	/**
	 * Successful deletion
	 */
	@Test
	public void deleteByIdSuccessTest() {

		when(orgVal.valId(anyString(), anyBoolean())).thenReturn("");
		when(orgDao.findById(anyLong())).thenReturn(org);
		when(org.getOffices()).thenReturn(Collections.emptyList());
		assertThat(orgService.deleteById("1")).isInstanceOf(SuccessView.class);
	}

}
