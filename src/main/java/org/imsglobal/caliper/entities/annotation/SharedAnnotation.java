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
public class SharedAnnotation extends Annotation {

	// TODO - this should be a list of LISGroup or LISPerson/s
	List<String> withAgents = Lists.newArrayList();

	public SharedAnnotation(String id) {
		super(id);
		setType("http://purl.imsglobal.org/caliper/v1/SharedAnnotation");
	}

	/**
	 * @return the users
	 */
	public List<String> getUsers() {
		return withAgents;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<String> users) {
		this.withAgents = users;
	}
}
