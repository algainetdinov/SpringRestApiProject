package com.ajax.restapiproject.doctype.dao;

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

import com.ajax.restapiproject.doctype.model.Doctype;

/**
 * Tests doctype DAO implementation
 * 
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class DoctypeDaoImplTests {

	@Autowired
	private DoctypeDaoImpl doctypeDao;

	@Autowired
	private TestEntityManager entityManager;

	private static Long docId;

	/**
	 * Create test doctypes
	 */
	@Before
	public void setup() {
		Doctype doc1 = new Doctype();
		doc1.setCode("10");
		doc1.setName("Passport");
		entityManager.persist(doc1);
		docId = doc1.getId();
	}

	/**
	 * Test retrieving of doctypes
	 */
	@Test
	public void findAllTest() {
		for (Doctype doc : doctypeDao.findAll()) {
			System.out.println(doc);
		}
		assertThat(doctypeDao.findAll()).hasAtLeastOneElementOfType(Doctype.class);
	}

	/**
	 * Test retrieving of doctype by code
	 */
	@Test
	public void findByCode() {
		assertThat(doctypeDao.findByCode("10")).isNotNull();
	}

	/**
	 * Test retrieving of doctype by Id
	 */
	@Test
	public void findById() {
		assertThat(doctypeDao.findById(docId)).isNotNull();
	}

}
