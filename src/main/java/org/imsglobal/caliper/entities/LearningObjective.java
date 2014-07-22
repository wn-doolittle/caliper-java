/**
 * 
 */
package org.imsglobal.caliper.entities;

/**
 * @author pnayak
 * 
 */
public class LearningObjective extends CaliperEntity {

	public LearningObjective(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/LearningObjective");
	}

}
