/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

import org.imsglobal.caliper.events.CaliperEvent;

/**
 * @author pnayak
 * 
 */
public class AnnotationEvent extends CaliperEvent {

	public static AnnotationEvent forAction(String action) {
		AnnotationEvent event = new AnnotationEvent();
		event.setAction(action);
		return event;
	}
}
