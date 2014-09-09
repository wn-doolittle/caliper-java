/**
 * 
 */
package org.imsglobal.caliper.events.assignable;

import org.imsglobal.caliper.entities.assignable.CaliperAssignableDigitalResource;
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

	/* (non-Javadoc)
	 * @see org.imsglobal.caliper.events.CaliperEvent#setObject(java.lang.Object)
	 */
	@Override
	public void setObject(Object object) {
		
		// TODO - Enforce restrictions on object type per metric profile
		if (getAction().equals(Action.started.toString())) {
			if (!(object instanceof CaliperAssignableDigitalResource)) {
				// TODO - throw proper exception
			}
		}
		
		super.setObject(object);
	}
}
