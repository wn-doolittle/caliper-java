/**
 * 
 */
package org.imsglobal.caliper.entities;

/**
 * @author pnayak
 *
 */
public class Agent extends CaliperEntity {
	
	/**
	 * @param id
	 * @param parentOrg
	 */
	public Agent(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/ctx/caliper/v1/Person");
	}

}
