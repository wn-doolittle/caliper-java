/**
 * 
 */
package org.imsglobal.caliper.entities.media;

import org.imsglobal.caliper.entities.CaliperEntity;

/**
 * @author pnayak
 * 
 */
public class MediaLocation extends CaliperEntity {

	/**
	 * @param currentTime
	 */
	public MediaLocation(long currentTime) {
		super();
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
