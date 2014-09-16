/**
 * 
 */
package org.imsglobal.caliper.entities.media;

import java.util.UUID;

import org.imsglobal.caliper.entities.CaliperEntity;

import com.google.common.base.Strings;

/**
 * @author pnayak
 * 
 */
public class MediaLocation extends CaliperEntity {

	/**
	 * @param currentTime
	 */
	public MediaLocation(String id, long currentTime) {
		
		super();
		
		// Set id and JSON-LD @type
		if (Strings.isNullOrEmpty(id)) {
			// auto generate a UUID
			setId("http://purl.imsglobal.org/caliper/v1/medialocation/" + new UUID(1000l, 500l));
		} else {
			setId(id);
		}
		setType("http://purl.imsglobal.org/caliper/v1/MediaLocation");
		
		this.currentTime = currentTime;
	}

	/**
	 * The time value (from beginning of media) that indicates the current
	 * location
	 */
	private long currentTime;

	/**
	 * @return the currentTime
	 */
	public long getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime
	 *            the currentTime to set
	 */
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

}
