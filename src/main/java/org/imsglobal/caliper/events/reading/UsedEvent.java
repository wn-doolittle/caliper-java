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
public class UsedEvent extends CaliperEvent {

	/**
	 * A short description of the threshold that was in effect to determine that
	 * the reader 'used' the frame (as opposed to a frameView only)
	 */
	private String useThreshold;

	/**
	 * Optional - embedded web content
	 */
	private DigitalResource embeddedContent;

	public UsedEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/UsedEvent");
		setType("UsedEvent");
		setAction("used");
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

	/**
	 * @return the useThreshold
	 */
	public String getUseThreshold() {
		return useThreshold;
	}

	/**
	 * @param useThreshold
	 *            the useThreshold to set
	 */
	public void setUseThreshold(String useThreshold) {
		this.useThreshold = useThreshold;
	}
}
