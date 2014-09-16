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

	/**
	 * 
	 */
	public CaliperMediaObject() {
		super();
		setType("http://purl.imsglobal.org/caliper/v1/CaliperMediaObject");
	}

}
