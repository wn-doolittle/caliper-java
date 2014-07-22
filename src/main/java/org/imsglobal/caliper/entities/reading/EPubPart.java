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
 *         A major structural division of a piece of writing, typically
 *         encapsulating a set of related chapters.
 *         http://www.idpf.org/epub/vocab/structure/#part
 * 
 */
public class EPubPart extends CaliperDigitalResource implements CreativeWork {

	public EPubPart(String id) {
		super();
		setId(id);
		setType("http://www.idpf.org/epub/vocab/structure/#part");
	}

}
