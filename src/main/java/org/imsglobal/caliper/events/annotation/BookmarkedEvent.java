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
		BookmarkedEvent event = new BookmarkedEvent("BookmarkedEvent");
		this.setAction("marked");
		return event;
	}

	public BookmarkedEvent forUnMark() {
		BookmarkedEvent event = new BookmarkedEvent("BookmarkedEvent");
		this.setAction("unmarked");
		return event;
	}

}
