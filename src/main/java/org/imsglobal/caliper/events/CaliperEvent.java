/**
 * 
 */
package org.imsglobal.caliper.events;

import com.google.gson.annotations.SerializedName;

/**
 * Base class for all Caliper Events
 * 
 * @author pnayak
 * 
 */
public class CaliperEvent {

	@SerializedName("@context")
	private String context;

	@SerializedName("@type")
	private String type;

	@SerializedName("action")
	private String action;

	@SerializedName("agent")
	private String agent;
	
	@SerializedName("activityContext")
	private String activityContext;

	@SerializedName("startedAtTime")
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

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

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
	 * @return the agent
	 */
	public String getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	/**
	 * @return the activityContext
	 */
	public String getActivityContext() {
		return activityContext;
	}

	/**
	 * @param activityContext
	 *            the activityContext to set
	 */
	public void setActivityContext(String activityContext) {
		this.activityContext = activityContext;
	}
}
