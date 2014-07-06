/**
 * 
 */
package org.imsglobal.caliper.events.reading;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

/**
 * @author pnayak
 * 
 */
public class NavigationEvent extends CaliperEvent {

	/**
	 * Optional - Describes the frame from which the navigation starts
	 */
	private DigitalResource fromResource;

	/**
	 * The way in which the reader performed the navigation. (PageTurn, Link,
	 * Scroll, Direct, SearchResult)
	 */
	private String operationType;

	/**
	 * 
	 */
	public NavigationEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent");
		setType("NavigationEvent");
	}

	/**
	 * @return the fromResource
	 */
	public DigitalResource getFromResource() {
		return fromResource;
	}

	/**
	 * @param fromResource
	 *            the fromResource to set
	 */
	public void setFromResource(DigitalResource fromResource) {
		this.fromResource = fromResource;
	}

	/**
	 * @return the operationType
	 */
	public String getOperationType() {
		return operationType;
	}

	/**
	 * @param operationType
	 *            the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

}
