package com.ajax.restapiproject.country.dao;

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

import com.ajax.restapiproject.country.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
public class CountryDaoImplTests {

	@Autowired
	private CountryDaoImpl countryDao;

	@Autowired
	private TestEntityManager entityManager;

	private static Long countryId;

	/**
	 * Create test countries
	 */
	@Before
	public void setup() {
		Country russia = new Country();
		russia.setCode("643");
		russia.setName("Russia");
		this.entityManager.persist(russia);
		countryId = russia.getId();

	}

	/**
	 * Test retrieving of countries
	 */
	@Test
	@Transactional
	public void findAllTest() {
		assertThat(countryDao.findAll()).hasAtLeastOneElementOfType(Country.class);
	}

	/**
	 * Test retrieving of country by code
	 */
	@Test
	@Transactional
	public void findByCode() {
		assertThat(countryDao.findByCode("643")).isNotNull();
	}

	/**
	 * Test retrieving of country by Id
	 */
	@Test
	@Transactional
	public void findById() {
		assertThat(countryDao.findById(countryId)).isNotNull();
	}

}
