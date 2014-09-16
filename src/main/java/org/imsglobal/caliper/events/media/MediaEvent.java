/**
 * 
 */
package org.imsglobal.caliper.events.media;

import org.imsglobal.caliper.entities.media.CaliperMediaObject;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.events.CaliperEvent;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pnayak
 * 
 */
public class MediaEvent extends CaliperEvent {

	@JsonIgnoreType
	public enum Action {
		started, paused, ended, jumpedTo, forwardedTo, rewindedTo, changedSpeed, 
		changedVolume, muted, unmuted, changedResolution, changedViewerSize, 
		enteredFullScreen, exitedFullScreen, openPopup, closePopup, 
		enabledCloseCaptioning, disabledCloseCaptioning
	}

	/**
	 * Describes the location within the media that is relevant to this event
	 */
	@JsonProperty("mediaLocation")
	private MediaLocation mediaLocation;

	public MediaEvent() {
		super();

		setContext("http://purl.imsglobal.org/ctx/caliper/v1/MediaEvent");
		setType("http://purl.imsglobal.org/caliper/v1/MediaEvent");
	}

	public static MediaEvent forAction(Action action) {
		MediaEvent event = new MediaEvent();
		event.setAction(action.toString());
		return event;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imsglobal.caliper.events.CaliperEvent#setObject(java.lang.Object)
	 */
	@Override
	public void setObject(Object object) {

		// TODO - Enforce restrictions on object type per metric profile
		if (getAction().equals(Action.started.toString())) {
			if (!(object instanceof CaliperMediaObject)) {
				// TODO - throw proper exception
			}
		}

		super.setObject(object);
	}

}
