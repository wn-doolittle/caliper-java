/**
 * 
 */
package org.imsglobal.caliper.entities.media;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.entities.schemadotorg.AudioObject;

/**
 * @author pnayak
 * 
 *         An audio object embedded in a web page.
 * 
 */
public class CaliperAudioObject extends CaliperMediaObject implements
		AudioObject {

	/**
	 * 
	 */
	public CaliperAudioObject() {
		super();
		setType("http://purl.imsglobal.org/caliper/v1/CaliperAudioObject");
	}

}
