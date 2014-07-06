/**
 * 
 */
package org.imsglobal.caliper.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author pnayak
 * 
 */
@JsonPropertyOrder({ "@id", "@type", "lastModifiedTime" })
public class CaliperEntity {

	@CaliperReference
	@JsonProperty("@id")
	String id;

	@JsonProperty("@type")
	String type;

	@JsonProperty("lastModifiedTime")
	private long lastModifiedAt;

	@JsonIgnore
	@JsonProperty("properties")
	private Map<String, Object> properties;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the lastModifiedAt
	 */
	public long getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * @param lastModifiedAt
	 *            the lastModifiedAt to set
	 */
	public void setLastModifiedAt(long lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * @return the properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
