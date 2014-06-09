/**
 * 
 */
package org.imsglobal.caliper.events;

/**
 * Base class for all Caliper Events
 * 
 * @author pnayak
 * 
 */
public class CaliperEvent {

	private String action;
	private long startedAt;

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the startedAt
	 */
	public long getStartedAt() {
		return startedAt;
	}

	/**
	 * @param startedAt
	 *            the startedAt to set
	 */
	public void setStartedAt(long startedAt) {
		this.startedAt = startedAt;
	}

}
