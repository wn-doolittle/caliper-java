/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

/**
 * @author pnayak
 * 
 */
public class HilightedEvent extends BaseAnnotationEvent {

	public HilightedEvent(String type) {
		this.setType(type);
	}

	public HilightedEvent forHilight() {
		HilightedEvent event = new HilightedEvent("annotate.hilight");
		return event;
	}

	public HilightedEvent forUnHilight() {
		HilightedEvent event = new HilightedEvent("annotate.unhilight");
		return event;
	}

}
