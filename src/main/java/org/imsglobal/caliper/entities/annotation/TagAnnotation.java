/**
 * 
 */
package org.imsglobal.caliper.entities.annotation;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author pnayak
 * 
 */
public class TagAnnotation extends Annotation {

	List<String> tags = Lists.newArrayList();

	public TagAnnotation(String id) {
		super(id);
		setType("http://purl.imsglobal.org/caliper/v1/TagAnnotation");
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
