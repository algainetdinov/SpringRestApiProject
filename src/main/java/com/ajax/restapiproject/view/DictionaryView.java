package com.ajax.restapiproject.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying dictionary records
 */
@ApiModel
@JsonPropertyOrder({"name", "code"})
public class DictionaryView {
	
	/**
	 * Dictionary record code
	 */
	@ApiModelProperty(value = "Dictionary record code", position = 2)
	@JsonProperty("code")
	public String code;
	
	/**
	 * Dictionary record name
	 */
	@ApiModelProperty(value = "Dictionary record name", position = 1)
	@JsonProperty("name")
	public String name;
	
	/**
	 * Constructor for initializing class fields
	 * @param code
	 * @param name
	 */
	public DictionaryView(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
