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
 *         A major structural division of a piece of writing
 *         http://www.idpf.org/epub/vocab/structure/#chapter
 * 
 */
public class EPubChapter extends CaliperDigitalResource implements CreativeWork {

	public EPubChapter(String id) {
		super();
		setId(id);
		setType("http://www.idpf.org/epub/vocab/structure/#chapter");
	}

}
