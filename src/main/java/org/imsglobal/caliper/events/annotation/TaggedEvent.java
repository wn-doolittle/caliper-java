/**
 * 
 */
package org.imsglobal.caliper.events.annotation;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author pnayak
 * 
 */
public class TaggedEvent extends AnnotationEvent {

	private List<String> tags = Lists.newArrayList();

	public TaggedEvent() {
		setContext("http://purl.imsglobal.org/ctx/caliper/v1/TaggedEvent");
		setType("http://purl.imsglobal.org/caliper/v1/TaggedEvent");
	}

	public static TaggedEvent forAction(String action) {
		TaggedEvent event = new TaggedEvent();
		event.setAction(action);
		return event;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
