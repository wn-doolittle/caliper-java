/**
 * 
 */
package org.imsglobal.caliper.entities.assessment;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
import org.imsglobal.caliper.entities.qti.Assessment;

/**
 * @author pnayak
 * 
 *         Caliper representation of an Assessment Item.
 * 
 *         Part of the Assessment Metric Profile
 * 
 */
public class CaliperAssessmentItem extends CaliperAssignableDigitalResource
		implements Assessment {

	public CaliperAssessmentItem(String id) {
		super();
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/AssessmentItem");
	}

}
