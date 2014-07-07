/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class CommentedEvent extends BaseAnnotationEvent {

	public CommentedEvent(String type) {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/CommentedEvent");
		setType(type);
	}

	public static CommentedEvent forAddComment() {
		CommentedEvent event = new CommentedEvent("CommentedEvent");
		event.setAction("commented");
		return event;
	}

	public static CommentedEvent forUpdateComment() {
		CommentedEvent event = new CommentedEvent("CommentedEvent");
		event.setAction("comment-updated");
		return event;
	}

	public static CommentedEvent forRemoveComment() {
		CommentedEvent event = new CommentedEvent("CommentedEvent");
		event.setAction("uncommented");
		return event;
	}

}
