package org.imsglobal.caliper.entities.annotation;

import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.entities.schemadotorg.Thing;

/**
 * @author pnayak
 * 
 *         The super-class of all Annotation types.
 * 
 *         Direct sub-types can include - Hilight, Attachment, etc. - all of
 *         which are specified in the Caliper Annotation Metric Profile
 * 
 */
public class Annotation extends CaliperEntity implements Thing {

	private Object target;

	public Annotation(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/Annotation");
	}

	/**
	 * @return the target
	 */
	public Object getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(Object target) {
		this.target = target;
	}
}
