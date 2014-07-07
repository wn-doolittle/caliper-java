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
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/SharedEvent");
		setType(type);
	}

	public static SharedEvent forShare() {
		SharedEvent event = new SharedEvent("SharedEvent");
		event.setAction("shared");
		return event;
	}

	public static SharedEvent forUnShare() {
		SharedEvent event = new SharedEvent("SharedEvent");
		event.setAction("unshared");
		return event;
	}

}
