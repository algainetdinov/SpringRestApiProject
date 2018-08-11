package com.ajax.restapiproject.office.dao;

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

import com.ajax.restapiproject.office.model.Office;
import com.ajax.restapiproject.organization.model.Organization;

/**
 * Tests office DAO implementation
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase 
@AutoConfigureTestEntityManager 
@Transactional
public class OfficeDaoImplTests {
	
	@Autowired
	private OfficeDaoImpl officeDao;
	
	@Autowired
	private TestEntityManager entityManager;
		
	private static Long officeId;
	
	/**
	 * Create test organization and office
	 */
	@Before
	public void setup() {
		Organization org = new Organization();
		org.setAddress("city street building");
		org.setName("orgName");
		org.setFullName("orgFullName");
		org.setInn("1111111111");
		org.setKpp("11111111");
		Office office = new Office();
		office.setAddress("city street building");
		office.setName("name");
		office.setOrg(org);
		org.getOffices().add(office);
		entityManager.persist(org);
		entityManager.persist(office);	
		officeId = office.getId();
	}
	
	/**
	 * Test retrieving of office
	 */
	@Test
	public void findByIdTest() {
		assertThat(officeDao.findById(officeId)).isNotNull();
	}
	
	/**
	 * Test retrieving of office by organization
	 */
	@Test
	public void findByOrgTest() {
		Office office = officeDao.findById(officeId);
		assertThat(officeDao.findByOrg(office)).hasAtLeastOneElementOfType(Office.class);
	}
	
	/**
	 * Test deleting of office
	 */
	@Test
	public void deleteTest() {
		int initialCount = officeDao.findAll().size();
		System.out.println(initialCount);
		System.out.println(officeDao.findById(officeId));
		Office office = officeDao.findAll().get(initialCount - 1);
		office.getOrg().getOffices().remove(office);
		office.setOrg(null);
		officeDao.delete(office);
		System.out.println(officeDao.findAll().size());
		assertThat(officeDao.findAll().size()).isEqualTo(initialCount - 1);
	}
	
	/**
	 * Test saving of office
	 */
	@Test
	public void saveTest () {
		int initialLength = officeDao.findAll().size();
		Office office = new Office();
		office.setName("Ajax");
		office.setAddress("Moscow Lenin str");
		officeDao.save(office);
		assertThat(officeDao.findAll().size()).isEqualTo(initialLength + 1);
	}
}

