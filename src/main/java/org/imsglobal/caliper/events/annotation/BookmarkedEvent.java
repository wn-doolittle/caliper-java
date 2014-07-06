/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class BookmarkedEvent extends BaseAnnotationEvent {

	public BookmarkedEvent(String type) {
		this.setType(type);
	}

	public BookmarkedEvent forMark() {
		BookmarkedEvent event = new BookmarkedEvent("annotate.mark");
		return event;
	}

	public BookmarkedEvent forUnMark() {
		BookmarkedEvent event = new BookmarkedEvent("annotate.unmark");
		return event;
	}

}
