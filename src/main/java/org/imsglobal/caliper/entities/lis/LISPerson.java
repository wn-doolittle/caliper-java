/**
 * 
 */
package org.imsglobal.caliper.entities.lis;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.CaliperEntity;

/**
 * @author pnayak
 * 
 */
public class LISPerson extends CaliperEntity implements CaliperAgent {

	public LISPerson(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/LISPerson");
	}

}
