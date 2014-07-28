/**
 * 
 */
package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.lis.LISOrganization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Base class for all Caliper Events
 * 
 * @author pnayak
 * 
 */
@JsonPropertyOrder({ "@context", "@type", "actor", "action", "object",
		"target", "generated", "startedAtTime", "endedAtTime", "edApp", "group" })
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
	@JsonProperty("actor")
	private CaliperAgent actor;

	/**
	 * Required - "Activity Context" from Metric Profile
	 */
	@JsonProperty("object")
	private Object object;

	/**
	 * Optional - "target" from Metric Profile
	 */
	@JsonProperty("target")
	private Object target;

	/**
	 * Optional - entity "generated" as result of action - from Metric Profile
	 */
	@JsonProperty("generated")
	private Object generated;

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

	@JsonProperty("group")
	private LISOrganization lisOrganization;

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
	 * @return the lisOrganization
	 */
	public LISOrganization getLisOrganization() {
		return lisOrganization;
	}

	/**
	 * @param lisOrganization
	 *            the lisOrganization to set
	 */
	public void setLisOrganization(LISOrganization lisOrganization) {
		this.lisOrganization = lisOrganization;
	}

	/**
	 * @return the actor
	 */
	public CaliperAgent getActor() {
		return actor;
	}

	/**
	 * @param actor
	 *            the actor to set
	 */
	public void setActor(CaliperAgent actor) {
		this.actor = actor;
	}

	/**
	 * @return the target
	 */
	public Object getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(Object target) {
		this.target = target;
	}

	/**
	 * @return the generated
	 */
	public Object getGenerated() {
		return generated;
	}

	/**
	 * @param generated
	 *            the generated to set
	 */
	public void setGenerated(Object generated) {
		this.generated = generated;
	}
}
