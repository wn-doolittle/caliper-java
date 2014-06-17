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

}
