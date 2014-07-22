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
 *         A major sub-division of a chapter
 *         http://www.idpf.org/epub/vocab/structure/#subchapter
 */
public class EPubSubChapter extends CaliperDigitalResource implements
		CreativeWork {

	public EPubSubChapter(String id) {
		super();
		setId(id);
		setType("http://www.idpf.org/epub/vocab/structure/#subchapter");
	}

}
