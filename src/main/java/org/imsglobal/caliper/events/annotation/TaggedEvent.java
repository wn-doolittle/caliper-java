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
public class TaggedEvent extends BaseAnnotationEvent {

	private List<String> tags = Lists.newArrayList();

	public TaggedEvent(String type) {
		this.setType(type);
	}

	public TaggedEvent forTag() {
		TaggedEvent event = new TaggedEvent("TaggedEvent");
		event.setAction("tagged");
		return event;
	}

	public TaggedEvent forUnTag() {
		TaggedEvent event = new TaggedEvent("TaggedEvent");
		event.setAction("untagged");
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
