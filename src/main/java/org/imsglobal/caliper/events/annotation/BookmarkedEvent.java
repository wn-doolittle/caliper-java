/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class BookmarkedEvent extends AnnotationEvent {

	public BookmarkedEvent() {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/BookmarkedEvent");
		setType("http://purl.imsglobal.org/caliper/v1/BookmarkedEvent");
	}

	public static BookmarkedEvent forAction(String action) {
		BookmarkedEvent event = new BookmarkedEvent();
		event.setAction(action);
		return event;
	}
}
