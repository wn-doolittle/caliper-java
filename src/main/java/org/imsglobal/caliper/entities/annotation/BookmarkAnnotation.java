/**
 * 
 */
package org.imsglobal.caliper.entities.annotation;

/**
 * @author pnayak
 * 
 */
public class BookmarkAnnotation extends Annotation {

	private String bookmarkNotes;

	public BookmarkAnnotation(String id) {
		super(id);
		setType("http://purl.imsglobal.org/caliper/v1/BookmarkAnnotation");
	}

	/**
	 * @return the bookmarkNotes
	 */
	public String getBookmarkNotes() {
		return bookmarkNotes;
	}

	/**
	 * @param bookmarkNotes
	 *            the bookmarkNotes to set
	 */
	public void setBookmarkNotes(String bookmarkNotes) {
		this.bookmarkNotes = bookmarkNotes;
	}
}
