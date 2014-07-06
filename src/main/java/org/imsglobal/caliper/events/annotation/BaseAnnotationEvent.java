/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

import org.imsglobal.caliper.entities.annotation.Location;
import org.imsglobal.caliper.events.CaliperEvent;

/**
 * @author pnayak
 * 
 */
public class BaseAnnotationEvent extends CaliperEvent {

	/**
	 * Optional (but highly recommended) - {@link Location} of the
	 * annotation within the referenced {@link DigitalResource}
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
