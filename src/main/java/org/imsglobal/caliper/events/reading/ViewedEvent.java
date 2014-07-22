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

	/**
	 * Optional - embedded web content
	 */
	private CaliperDigitalResource embeddedContent;

	public ViewedEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/ViewedEvent");
		setType("ViewedEvent");
		setAction("viewed");
	}

	/**
	 * @return the embeddedContent
	 */
	public CaliperDigitalResource getEmbeddedContent() {
		return embeddedContent;
	}

	/**
	 * @param embeddedContent
	 *            the embeddedContent to set
	 */
	public void setEmbeddedContent(CaliperDigitalResource embeddedContent) {
		this.embeddedContent = embeddedContent;
	}
}
