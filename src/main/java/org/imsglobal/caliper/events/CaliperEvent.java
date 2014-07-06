/**
 * 
 */
package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.Course;
import org.imsglobal.caliper.entities.SoftwareApplication;

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
	private Agent agent;

	@SerializedName("object")
	private Object object;

	@SerializedName("edApp")
	private SoftwareApplication edApp;

	@SerializedName("group")
	private Course course;

	@SerializedName("startedAtTime")
	private long startedAt;

	@SerializedName("endedAtTime")
	private long endedAt;

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
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return the edApp
	 */
	public SoftwareApplication getEdApp() {
		return edApp;
	}

	/**
	 * @param edApp
	 *            the edApp to set
	 */
	public void setEdApp(SoftwareApplication edApp) {
		this.edApp = edApp;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the endedAt
	 */
	public long getEndedAt() {
		return endedAt;
	}

	/**
	 * @param endedAt
	 *            the endedAt to set
	 */
	public void setEndedAt(long endedAt) {
		this.endedAt = endedAt;
	}
}
