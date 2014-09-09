/**
 * 
 */
package org.imsglobal.caliper.events.outcome;

import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * @author pnayak
 * 
 */
public class OutcomeEvent extends CaliperEvent {

	@JsonIgnoreType
	public enum Action {
		graded
	}

	public OutcomeEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/OutcomeEvent");
		setType("http://purl.imsglobal.org/caliper/v1/OutcomeEvent");
	}

	public static OutcomeEvent forAction(Action action) {
		OutcomeEvent event = new OutcomeEvent();
		event.setAction(action.toString());
		return event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imsglobal.caliper.events.CaliperEvent#setObject(java.lang.Object)
	 */
	@Override
	public void setObject(Object object) {

		// TODO - Enforce restrictions on object type per metric profile
		if (getAction().equals(Action.graded.toString())) {
			if (!(object instanceof Attempt)) {
				// TODO - throw proper exception
			}
		}

		super.setObject(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imsglobal.caliper.events.CaliperEvent#setGenerated(java.lang.Object)
	 */
	@Override
	public void setGenerated(Object generated) {

		// TODO - Enforce restrictions on object type per metric profile
		if (getAction().equals(Action.graded.toString())) {
			if (!(generated instanceof Result)) {
				// TODO - throw proper exception
			}
		}
		super.setGenerated(generated);
	}
}
