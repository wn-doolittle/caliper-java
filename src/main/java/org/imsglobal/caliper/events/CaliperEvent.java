/**
 * 
 */
package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.Agent;
import org.imsglobal.caliper.entities.Organization;
import org.imsglobal.caliper.entities.SoftwareApplication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Base class for all Caliper Events
 * 
 * @author pnayak
 * 
 */
@JsonPropertyOrder({ "@context", "@type", "action", "agent", "object",
		"startedAtTime", "endedAtTime" })
public class CaliperEvent {

	// Core properties

	/**
	 * Required - the JSON-LD context for the CaliperEvent
	 */
	@JsonProperty("@context")
	private String context;

	/**
	 * Required - the type of the CaliperEvent
	 */
	@JsonProperty("@type")
	private String type;

	/**
	 * Required - Action performed by Agent From Metric Profile
	 */
	@JsonProperty("action")
	private String action;

	/**
	 * Required - Agent (User, System) that performed the action
	 */
	@JsonProperty("agent")
	private Agent agent;

	/**
	 * Required - "Activity Context" from Metric Profile
	 */
	@JsonProperty("object")
	private Object object;

	/**
	 * Required time in milliseconds that the event was started at
	 */
	@JsonProperty("startedAtTime")
	private long startedAt;

	/**
	 * An optional time in milliseconds that the event ended at
	 */
	@JsonProperty("endedAtTime")
	private long endedAt;

	// Contextual Properties ("Learning Context")

	@JsonProperty("edApp")
	private SoftwareApplication edApp;

	@JsonProperty("organization")
	private Organization organization;

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

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
