/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class HighlightedEvent extends AnnotationEvent {

	public HighlightedEvent() {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/HighlightedEvent");
		setType("http://purl.imsglobal.org/caliper/v1/HighlightedEvent");
	}

	public static HighlightedEvent forAction(String action) {
		HighlightedEvent event = new HighlightedEvent();
		event.setAction(action);
		return event;
	}
}
