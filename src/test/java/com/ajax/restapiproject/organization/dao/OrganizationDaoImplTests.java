package com.ajax.restapiproject.organization.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ajax.restapiproject.organization.model.Organization;

/**
 * Tests organization DAO implementation
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase 
@AutoConfigureTestEntityManager 
@Transactional
public class OrganizationDaoImplTests {
	
	@Autowired
	private OrganizationDaoImpl organizationDao;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private static Long orgId;
	
	/**
	 * Create test organization
	 */
	@Before
	public void setup() {
		Organization org = new Organization();
		org.setAddress("city street building");
		org.setName("orgName");
		org.setFullName("orgFullName");
		org.setInn("1111111111");
		org.setKpp("11111111");	
		entityManager.persist(org);
		orgId = org.getId();
	}
	
	/**
	 * Test retrieving of organization
	 */
	@Test
	public void findByIdTest() {
		assertThat(organizationDao.findById(orgId)).isNotNull();
	}
	
	/**
	 * Test retrieving of organization by name
	 */
	@Test
	public void findByNameTest() {
		Organization org = organizationDao.findById(orgId);
		assertThat(organizationDao.findByName(org)).hasAtLeastOneElementOfType(Organization.class);
	}
	
	/**
	 * Test deleting of organization
	 */
	@Test
	public void deleteTest() {
		int initialCount = organizationDao.findAll().size();
		organizationDao.delete(organizationDao.findById(orgId));
		assertThat(organizationDao.findAll().size()).isEqualTo(initialCount - 1);
	}
	
	/**
	 * Test saving of organization
	 */
	@Test
	public void saveTest () {
		int initialLength = organizationDao.findAll().size();
		Organization organization = new Organization();
		organization.setName("Ajax");
		organization.setFullName("Ajax inc.");
		organization.setInn("1111111111");
		organization.setKpp("22222222");
		organization.setAddress("Moscow Lenin str");
		organizationDao.save(organization);
		assertThat(organizationDao.findAll().size()).isEqualTo(initialLength + 1);
	}
}

