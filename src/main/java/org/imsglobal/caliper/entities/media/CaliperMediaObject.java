/**
 * 
 */
package org.imsglobal.caliper.entities.media;

import org.imsglobal.caliper.entities.CaliperDigitalResource;

/**
 * @author pnayak
 * 
 *         An image, video, or audio object embedded in a web page.
 * 
 */
public abstract class CaliperMediaObject extends CaliperDigitalResource {

	private long duration;

	/**
	 * 
	 */
	public CaliperMediaObject(String id) {
		super();
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/CaliperMediaObject");
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

}
