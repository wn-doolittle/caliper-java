package org.imsglobal.caliper.entities.annotation;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
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

	public Annotation(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/Annotation");
	}

	/**
	 * {@link Location} of the annotation within the referenced
	 * {@link CaliperDigitalResource}
	 */
	private Location location;

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}
