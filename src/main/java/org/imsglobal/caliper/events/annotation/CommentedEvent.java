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
		this.setType(type);
	}

	public CommentedEvent forAddComment() {
		CommentedEvent event = new CommentedEvent("annotate.comment.add");
		return event;
	}

	public CommentedEvent forUpdateComment() {
		CommentedEvent event = new CommentedEvent("annotate.comment.update");
		return event;
	}

	public CommentedEvent forRemoveComment() {
		CommentedEvent event = new CommentedEvent("annotate.comment.remove");
		return event;
	}

}
