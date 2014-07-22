/**
 * 
 */
package org.imsglobal.caliper.events.reading;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pnayak
 * 
 */
public class NavigationEvent extends CaliperEvent {

	/**
	 * Describes the resource from which the navigation starts
	 */
	@JsonProperty("navigatedFrom")
	private CaliperDigitalResource fromResource;

	public NavigationEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent");
		setType("NavigationEvent");
		setAction("navigatedTo");
	}

	/**
	 * @return the fromResource
	 */
	public CaliperDigitalResource getFromResource() {
		return fromResource;
	}

	/**
	 * @param fromResource
	 *            the fromResource to set
	 */
	public void setFromResource(CaliperDigitalResource fromResource) {
		this.fromResource = fromResource;
	}
}
