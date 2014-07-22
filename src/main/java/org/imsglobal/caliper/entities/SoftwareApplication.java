/**
 * 
 */
package org.imsglobal.caliper.entities;

import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

/**
 * @author pnayak
 * 
 */
@CaliperLearningContext
public class SoftwareApplication extends CaliperEntity implements CreativeWork {
	
	public SoftwareApplication(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/SoftwareApplication");
	}

}
