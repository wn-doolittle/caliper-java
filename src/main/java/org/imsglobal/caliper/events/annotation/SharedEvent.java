/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class SharedEvent extends BaseAnnotationEvent {

	public SharedEvent(String type) {
		this.setType(type);
	}

	public SharedEvent forShare() {
		SharedEvent event = new SharedEvent("annotate.share");
		return event;
	}

	public SharedEvent forUnShare() {
		SharedEvent event = new SharedEvent("annotate.unshare");
		return event;
	}

}
