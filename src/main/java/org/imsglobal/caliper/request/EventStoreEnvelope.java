/**
 * 
 */
package org.imsglobal.caliper.request;

import org.imsglobal.caliper.events.CaliperEvent;

/**
 * @author pnayak
 *
 */
public class EventStoreEnvelope {
	
	private String type;
	private String time;
	private CaliperEvent data;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the data
	 */
	public CaliperEvent getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(CaliperEvent data) {
		this.data = data;
	}

}
