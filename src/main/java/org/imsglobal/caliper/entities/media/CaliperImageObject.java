/**
 * 
 */
package org.imsglobal.caliper.entities.media;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.entities.schemadotorg.ImageObject;

/**
 * @author pnayak
 * 
 *         An image object embedded in a web page.
 * 
 */
public class CaliperImageObject extends CaliperMediaObject implements
		ImageObject {

	/**
	 * 
	 */
	public CaliperImageObject() {
		super();
		setType("http://purl.imsglobal.org/caliper/v1/CaliperImageObject");
	}

}
