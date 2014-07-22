/**
 * 
 */
package org.imsglobal.caliper.entities.schemadotorg;

import org.imsglobal.caliper.entities.CaliperDigitalResource;

/**
 * @author pnayak
 * 
 */
public class WebPage extends CaliperDigitalResource implements CreativeWork {

	public WebPage(String id) {
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/WebPage");
	}

}
