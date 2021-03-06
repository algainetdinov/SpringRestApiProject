package com.ajax.restapiproject.user.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ajax.restapiproject.office.model.Office;
import com.ajax.restapiproject.organization.model.Organization;
import com.ajax.restapiproject.user.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class UserDaoImplTests {

	@Autowired
	private UserDaoImpl userDao;

	@Autowired
	private TestEntityManager entityManager;

	@Mock
	private User user;

	private static Long userId;

	/**
	 * Create test organization, office and user
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
		User user = new User();
		user.setFirstName("John");
		user.setPosition("manager");
		user.setOffice(office);
		office.getUsers().add(user);
		entityManager.persist(user);
		entityManager.persist(office);
		entityManager.persist(org);
		userId = user.getId();
	}

	/**
	 * Test retrieving of user
	 */
	@Test
	public void findByIdTest() {

		assertThat(userDao.findById(userId)).isNotNull();
	}

	/**
	 * Test retrieving of user by office
	 */
	@Test
	public void findByOfficeIdTest() {
		User user = userDao.findById(userId);
		assertThat(userDao.findByOffice(user)).hasAtLeastOneElementOfType(User.class);
	}

	/**
	 * Test deleting of user
	 */
	@Test
	public void deleteTest() {
		int initialLength = userDao.findAll().size();
		User user = userDao.findById(userId);
		user.getOffice().getUsers().remove(user);
		user.setOffice(null);
		userDao.delete(userDao.findById(userId));
		assertThat(userDao.findAll().size()).isEqualTo(initialLength - 1);
	}

	/**
	 * Test saving of user
	 */
	@Test
	public void saveTest() {
		int initialLength = userDao.findAll().size();
		User user = new User();
		user.setFirstName("John");
		user.setPosition("manager");
		userDao.save(user);
		assertThat(userDao.findAll().size()).isEqualTo(initialLength + 1);
	}
}
