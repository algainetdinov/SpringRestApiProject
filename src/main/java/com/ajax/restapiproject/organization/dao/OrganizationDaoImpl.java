package com.ajax.restapiproject.organization.dao;

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

/**
 * Organization DAO implementation
 */
import com.ajax.restapiproject.organization.model.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final EntityManager em;
	
	@Autowired
	OrganizationDaoImpl (EntityManager em) {
		this.em=em;
	}

	@Override
	/**
	 * Load an organization by provided Id 
	 */
	public Organization loadById(Long id) {
		logger.info("Organization to be loaded by Id: " + id);
		return em.find(Organization.class, id);
	}

	@Override
	/**
	 * Load an organization by provided name 
	 */
	public List<Organization> loadByName(Organization org) {
		
		logger.info("Organization to be loaded by name: " + org);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
		
		Root<Organization> orgRoot = cq.from(Organization.class);
		
		List<Predicate> preList = new ArrayList<Predicate>();
		
		Predicate namePre = cb.equal(orgRoot.get("name"), org.getName());
		
		preList.add(namePre);
		
		if (org.getInn()!=null) {
			Predicate innPre = cb.equal(orgRoot.get("inn"), org.getInn());
			preList.add(innPre);
		}
		
		if (org.getIsActive()!=null) {
			Predicate isActivePre = cb.equal(orgRoot.get("isActive"), org.getIsActive());
			preList.add(isActivePre);
		}
		
		cq.where(cb.and(preList.toArray(new Predicate[preList.size()])));
		
		TypedQuery<Organization> query = em.createQuery(cq);
		return query.getResultList();	
	}
	
	@Override
	/**
	 * Save provided organization
	 */
	public void save(Organization org) {
		
		logger.info("Organization to be saved: " + org);
		
		em.persist(org);
	}
}
