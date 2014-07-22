/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class HilightedEvent extends AnnotationEvent {

	public HilightedEvent() {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/HilightedEvent");
		setType("http://purl.imsglobal.org/caliper/v1/HilightedEvent");
	}

	public static HilightedEvent forAction(String action) {
		HilightedEvent event = new HilightedEvent();
		event.setAction(action);
		return event;
	}
}
