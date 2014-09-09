/**
 * 
 */
package org.imsglobal.caliper.events.assignable;

import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * @author pnayak
 * 
 */
public class AssignableEvent extends CaliperEvent {

	@JsonIgnoreType
	public enum Action {
		started, submitted, completed, abandoned, reviewed, activated, deactivated, showed, hid
	}

	public AssignableEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/AssignableEvent");
		setType("http://purl.imsglobal.org/caliper/v1/AssignableEvent");
	}

	public static AssignableEvent forAction(Action action) {
		AssignableEvent event = new AssignableEvent();
		event.setAction(action.toString());
		return event;
	}
}
