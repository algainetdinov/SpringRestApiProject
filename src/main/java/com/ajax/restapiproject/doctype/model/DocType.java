package com.ajax.restapiproject.doctype.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ajax.restapiproject.model.DictionaryEntity;

/**
 * Dictionary for storing document types provided by 
 * Приложение №3 к Требованиям к оформлению
 * документов, представляемых в регистрирующий орган.
 * Variables and methods inherited from 
 * DictionaryEntity class
 */
@Entity
@Table(name = "doctype")
@AttributeOverride(column = @Column(name = "name", unique = true, length = 128, nullable = false), name = "name")
public class DocType extends DictionaryEntity{}