/**
 * 
 */
package org.imsglobal.caliper.request;

import org.imsglobal.caliper.events.Event;

/**
 * @author pnayak
 * 
 */
public class EventStoreEnvelope {

	private String id;
	private String type;
	private String time;
	private Event data;

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
	public Event getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Event data) {
		this.data = data;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
