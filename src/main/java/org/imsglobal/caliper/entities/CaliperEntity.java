/**
 * 
 */
package org.imsglobal.caliper.entities;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.gson.annotations.SerializedName;

/**
 * @author pnayak
 * 
 */
public class CaliperEntity {

	@CaliperReference
	@SerializedName("@id")
	String id;

	@SerializedName("@type")
	String type;

	@SerializedName("lastModifiedTime")
	private long lastModifiedAt;

	@JsonIgnore
	@SerializedName("properties")
	private Map<String, String> properties;

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
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
