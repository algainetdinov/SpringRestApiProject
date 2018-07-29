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

@Repository
public class OfficeDaoImpl implements OfficeDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final EntityManager em;
	
	@Autowired
	OfficeDaoImpl (EntityManager em) {
		this.em=em;
	}

	@Override
	/**
	 * Load an office by provided Id 
	 */
	public Office loadById(Long id) {
		logger.info("Office to be loaded by Id: " + id);
		return em.find(Office.class, id);
	}

	@Override
	/**
	 * Load an office by provided name 
	 */
	public List<Office> loadByOrg(Office office) {
		
		logger.info("Office to be loaded by organization: " + office);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Office> cq = cb.createQuery(Office.class);
		
		Root<Office> orgRoot = cq.from(Office.class);
		
		List<Predicate> preList = new ArrayList<Predicate>();
		
		Predicate orgPre = cb.equal(orgRoot.get("org"), office.getOrg());
		
		preList.add(orgPre);
		
		if (office.getName()!=null) {
			Predicate namePre = cb.equal(orgRoot.get("name"), office.getName());
			preList.add(namePre);
		}
		
		if (office.getPhone()!=null) {
			Predicate phonePre = cb.equal(orgRoot.get("phone"), office.getPhone());
			preList.add(phonePre);
		}

		if (office.getIsActive()!=null) {
			Predicate isActivePre = cb.equal(orgRoot.get("isActive"), office.getIsActive());
			preList.add(isActivePre);
		}
		
		cq.where(cb.and(preList.toArray(new Predicate[preList.size()])));
		
		TypedQuery<Office> query = em.createQuery(cq);
		return query.getResultList();	
	}
	
	@Override
	/**
	 * Save provided organization
	 */
	public void save(Office office) {
		
		logger.info("Office to be saved: " + office);
		
		em.persist(office);
	}
	
	public void delete (Office office) {
		
		logger.info("Office to be deleted: " + office);
		
		em.remove(office);
	}
}
