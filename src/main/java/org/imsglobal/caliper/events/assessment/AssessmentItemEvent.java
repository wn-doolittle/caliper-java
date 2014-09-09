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
public class AssessmentItemEvent extends CaliperEvent {
	@JsonIgnoreType
	public enum Action {
		started, paused, restarted, submitted
	}

	public AssessmentItemEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentItemEvent");
		setType("http://purl.imsglobal.org/caliper/v1/AssessmentItemEvent");
	}

	public static AssessmentItemEvent forAction(Action action) {
		AssessmentItemEvent event = new AssessmentItemEvent();
		event.setAction(action.toString());
		return event;
	}
}
