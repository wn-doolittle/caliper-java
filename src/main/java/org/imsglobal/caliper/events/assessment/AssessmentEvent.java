/**
 * 
 */
package org.imsglobal.caliper.events.assessment;

import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * @author pnayak
 * 
 */
public class AssessmentEvent extends CaliperEvent {

	@JsonIgnoreType
	public enum Action {
		started, paused, restarted, submitted
	}

	public AssessmentEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentEvent");
		setType("http://purl.imsglobal.org/caliper/v1/AssessmentEvent");
	}

	public static AssessmentEvent forAction(Action action) {
		AssessmentEvent event = new AssessmentEvent();
		event.setAction(action.toString());
		return event;
	}
}
