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

	public AnnotationEvent() {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/AnnotationEvent");
		setType("http://purl.imsglobal.org/caliper/v1/AnnotationEvent");
	}

	public static AnnotationEvent forAction(String action) {
		AnnotationEvent event = new AnnotationEvent();
		event.setAction(action);
		return event;
	}
}
