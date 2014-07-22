/**
 * 
 */
package org.imsglobal.caliper.events.reading;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.events.CaliperEvent;

/**
 * @author pnayak
 * 
 */
public class NavigationEvent extends CaliperEvent {

	/**
	 * Optional - Describes the frame from which the navigation starts
	 */
	private CaliperDigitalResource fromResource;

	/**
	 * The way in which the reader performed the navigation. (PageTurn, Link,
	 * Scroll, Direct, SearchResult)
	 */
	private String operationType;

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
