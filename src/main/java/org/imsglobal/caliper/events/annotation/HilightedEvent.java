/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class HilightedEvent extends BaseAnnotationEvent {

	public HilightedEvent(String type) {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/HilightedEvent");
		setType(type);
	}

	public static HilightedEvent forHilight() {
		HilightedEvent event = new HilightedEvent("HilightedEvent");
		event.setAction("hilighted");
		return event;
	}

	public static HilightedEvent forUnHilight() {
		HilightedEvent event = new HilightedEvent("HilightedEvent");
		event.setAction("unhilighted");
		return event;
	}

}
