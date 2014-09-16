/**
 * 
 */
package org.imsglobal.caliper.entities.media;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.entities.schemadotorg.VideoObject;

/**
 * @author pnayak
 * 
 *         A Video object embedded in a web page.
 * 
 */
public class CaliperVideoObject extends CaliperMediaObject implements
		VideoObject {

	/**
	 * 
	 */
	public CaliperVideoObject() {
		super();
		setType("http://purl.imsglobal.org/caliper/v1/CaliperVideoObject");
	}

}
