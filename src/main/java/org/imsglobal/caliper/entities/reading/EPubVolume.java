/**
 * 
 */
package org.imsglobal.caliper.entities.reading;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

/**
 * @author pnayak
 * 
 *         Representation of an EPUB 3 Volume
 * 
 *         A component of a collection
 *         http://www.idpf.org/epub/vocab/structure/#volume
 * 
 */
public class EPubVolume extends CaliperDigitalResource implements CreativeWork {

	public EPubVolume(String id) {
		super();
		setId(id);
		setType("http://www.idpf.org/epub/vocab/structure/#volume");
	}
}
