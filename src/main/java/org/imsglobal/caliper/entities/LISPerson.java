/**
 * 
 */
package org.imsglobal.caliper.entities;

/**
 * @author pnayak
 * 
 */
public class LISPerson extends CaliperEntity {

	public LISPerson(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/ctx/caliper/v1/LISPerson");
	}

}
