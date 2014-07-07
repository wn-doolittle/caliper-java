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
		SharedEvent event = new SharedEvent("SharedEvent");
		event.setAction("shared");
		return event;
	}

	public SharedEvent forUnShare() {
		SharedEvent event = new SharedEvent("SharedEvent");
		event.setAction("unshared");
		return event;
	}

}
