package com.ajax.restapiproject.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/** 
 * Base entity provides common fields for subclasses
 */
@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
     * Hibernate technical field
     */
    @Version
    private Integer version;
}
