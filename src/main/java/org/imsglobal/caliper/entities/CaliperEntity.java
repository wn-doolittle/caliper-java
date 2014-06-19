/**
 * 
 */
package org.imsglobal.caliper.entities;

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

}
