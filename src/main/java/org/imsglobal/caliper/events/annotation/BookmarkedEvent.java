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
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/BookmarkedEvent");
		setType(type);
	}

	public static BookmarkedEvent forMark() {
		BookmarkedEvent event = new BookmarkedEvent("BookmarkedEvent");
		event.setAction("marked");
		return event;
	}

	public static BookmarkedEvent forUnMark() {
		BookmarkedEvent event = new BookmarkedEvent("BookmarkedEvent");
		event.setAction("unmarked");
		return event;
	}

}
