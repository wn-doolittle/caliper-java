/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class SharedEvent extends AnnotationEvent {

	public SharedEvent() {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/SharedEvent");
		setType("http://purl.imsglobal.org/caliper/v1/SharedEvent");
	}

	public static SharedEvent forAction(String action) {
		SharedEvent event = new SharedEvent();
		event.setAction(action);
		return event;
	}
}
