/**
 * 
 */
package org.imsglobal.caliper.entities.assessment;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.entities.qti.Assessment;

/**
 * @author pnayak
 * 
 *         Caliper representation of an Assessment.
 * 
 *         Part of the Assessment Metric Profile
 * 
 */
public class CaliperAssessment extends CaliperAssignableDigitalResource
		implements Assessment {

	public CaliperAssessment(String id) {
		super(id);
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/Assessment");
	}

}
