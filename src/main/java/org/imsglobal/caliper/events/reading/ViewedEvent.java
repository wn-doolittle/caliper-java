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
public class ViewedEvent extends CaliperEvent {

	public ViewedEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/ViewedEvent");
		setType("http://purl.imsglobal.org/caliper/v1/ViewedEvent");
		setAction("viewed");
	}
}
