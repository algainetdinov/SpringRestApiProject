package com.ajax.restapiproject.office.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajax.restapiproject.office.model.Office;

/**
 * Office DAO implementation
 * @author Al
 *
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final EntityManager em;
	
	/**
	 * Constructor to set final fields
	 * @param em
	 */
	@Autowired
	OfficeDaoImpl (EntityManager em) {
		
		this.em = em;
	}

	/**
	 * Load an office by provided Id 
	 */
	@Override
	public Office findById(Long id) {
		
		logger.info("Office to be loaded by Id: " + id);
		
		return em.find(Office.class, id);
	}

	/**
	 * Load an office by organization
	 */
	@Override
	public List<Office> findByOrg(Office office) {
		
		logger.info("Office to be loaded by organization: " + office);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Office> cq = cb.createQuery(Office.class);
		
		Root<Office> officeRoot = cq.from(Office.class);
		
		List<Predicate> preList = new ArrayList<Predicate>();
		
		Predicate orgPre = cb.equal(officeRoot.get("org"), office.getOrg());
		
		preList.add(orgPre);
		
		if (office.getName()!=null) {
			Predicate namePre = cb.equal(officeRoot.get("name"), office.getName());
			preList.add(namePre);
		}
		
		if (office.getPhone()!=null) {
			Predicate phonePre = cb.equal(officeRoot.get("phone"), office.getPhone());
			preList.add(phonePre);
		}

		if (office.getIsActive()!=null) {
			Predicate isActivePre = cb.equal(officeRoot.get("isActive"), office.getIsActive());
			preList.add(isActivePre);
		}
		
		cq.where(cb.and(preList.toArray(new Predicate[preList.size()])));
		
		TypedQuery<Office> query = em.createQuery(cq);
		
		return query.getResultList();	
	}
	
	/**
	 * Save office
	 */
	@Override
	public void save(Office office) {
		
		logger.info("Office to be saved: " + office);
		
		em.persist(office);
	}
	
	/**
	 * Delete office
	 */
	@Override
	public void delete(Office office) {
		
		logger.info("Office to be deleted: " + office);
		
		em.remove(office);
	}
	
	/**
	 * Retrieve list of offices
	 * @return
	 */
	@Override
	public List <Office> findAll() {
		
		TypedQuery<Office> query = em.createQuery("SELECT o from Office o", Office.class);
		
		return query.getResultList();
	}
}
