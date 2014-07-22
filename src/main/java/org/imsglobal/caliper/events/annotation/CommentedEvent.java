/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class CommentedEvent extends AnnotationEvent {

	public CommentedEvent() {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/CommentedEvent");
		setType("http://purl.imsglobal.org/caliper/v1/CommentedEvent");
	}

	public static CommentedEvent forAction(String action) {
		CommentedEvent event = new CommentedEvent();
		event.setAction(action);
		return event;
	}
}
