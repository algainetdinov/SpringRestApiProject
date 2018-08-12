package com.ajax.restapiproject.document.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

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
import com.ajax.restapiproject.document.model.Document;

/**
 * Tests document DAO implementation
 * 
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class DocumentDaoImplTests {

	@Autowired
	private DocumentDaoImpl documentDao;

	@Autowired
	private TestEntityManager entityManager;

	/**
	 * Create test doctypes
	 */
	@Before
	public void setup() {
		Doctype type = new Doctype();
		type.setCode("11");
		type.setName("Birth certificate");
		Document doc1 = new Document();
		doc1.setDocNumber("11111");
		doc1.setDocDate(new Date());
		doc1.setType(type);
		Document doc2 = new Document();
		doc2.setDocNumber("22222");
		doc2.setDocDate(new Date());
		doc2.setType(type);
		entityManager.persist(type);
		entityManager.persist(doc1);
		entityManager.persist(doc2);
	}

	/**
	 * Test deleting of document
	 */
	@Test
	public void deleteTest() {
		int initialCount = documentDao.findAll().size();
		Document doc = documentDao.findAll().get(initialCount - 1);
		documentDao.delete(doc);
		assertThat(documentDao.findAll().size()).isEqualTo(initialCount - 1);
	}

	/**
	 * Test retrieving of documents
	 */
	@Test
	public void findAllTest() {
		assertThat(documentDao.findAll()).hasAtLeastOneElementOfType(Document.class);
	}

}
