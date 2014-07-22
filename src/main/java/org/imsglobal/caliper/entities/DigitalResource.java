package org.imsglobal.caliper.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * @author pnayak
 * 
 */
public class DigitalResource extends CaliperEntity {

//	@JsonProperty("partOf")
//	private Object parentRef;

	@JsonProperty("name")
	private String name;

//	@JsonProperty("alignedLearningObjectives")
//	private List<String> alignedLearningObjectives = Lists.newArrayList();
//
//	@JsonProperty("keywords")
//	private List<String> keywords = Lists.newArrayList();
//
//	@JsonProperty("about")
//	private String about;
//
//	@JsonProperty("inLanguage")
//	private String inLanguage;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
