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
public class ShareAnnotation extends Annotation {

	// TODO - this should be a list of LISGroup or LISPerson/s
	List<String> users = Lists.newArrayList();

	public ShareAnnotation(String id) {
		super(id);
		setType("http://purl.imsglobal.org/caliper/v1/ShareAnnotation");
	}

	/**
	 * @return the users
	 */
	public List<String> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<String> users) {
		this.users = users;
	}
}
