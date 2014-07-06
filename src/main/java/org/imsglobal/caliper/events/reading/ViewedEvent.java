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
public class ViewedEvent extends CaliperEvent {

	/**
	 * Optional - embedded web content
	 */
	private DigitalResource embeddedContent;

	public ViewedEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/ViewedEvent");
		setType("ViewedEvent");
	}

	/**
	 * @return the embeddedContent
	 */
	public DigitalResource getEmbeddedContent() {
		return embeddedContent;
	}

	/**
	 * @param embeddedContent
	 *            the embeddedContent to set
	 */
	public void setEmbeddedContent(DigitalResource embeddedContent) {
		this.embeddedContent = embeddedContent;
	}
}
