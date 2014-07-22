package org.imsglobal.caliper.entities;

import java.util.List;

import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * @author pnayak
 * 
 *         Caliper representation of a CreativeWork
 *         (https://schema.org/CreativeWork)
 * 
 *         We add on learning specific attributes, including a list of
 *         {@link LearningObjective} learning objectives and a list of
 *         {@link String} keywords
 * 
 *         In addition, we add a the following attributes:
 * 
 *         name (https://schema.org/name) -the name of the resource,
 * 
 *         about (https://schema.org/about) - the subject matter of the resource
 * 
 *         language (https://schema.org/Language) - Natural languages such as
 *         Spanish, Tamil, Hindi, English, etc. and programming languages such
 *         as Scheme and Lisp
 * 
 */
public class CaliperDigitalResource extends CaliperEntity implements
		CreativeWork {

	@JsonProperty("name")
	private String name;

	@JsonProperty("partOf")
	private Object parentRef;

	@JsonProperty("alignedLearningObjectives")
	private List<LearningObjective> alignedLearningObjectives = Lists
			.newArrayList();

	@JsonProperty("keywords")
	private List<String> keywords = Lists.newArrayList();

	@JsonProperty("about")
	private String about;

	@JsonProperty("language")
	private String language;

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
