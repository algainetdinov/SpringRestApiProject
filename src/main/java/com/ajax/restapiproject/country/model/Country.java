package com.ajax.restapiproject.country.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ajax.restapiproject.model.DictionaryEntity;

/**
 * Entity for storing list of countries with their 
 * respective codes provided by ISO 3166. Variables 
 * and methods inherited from DictionaryEntity class
 */
@Entity
@Table(name = "country")
@AttributeOverride(column = @Column(name = "code", unique = true, length = 3, nullable = false), name = "code")
public class Country extends DictionaryEntity{

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Country [getCode()=" + getCode() + ", getName()=" + getName() + ", getId()=" + getId() + "]";
	}
}