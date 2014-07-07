/**
 * 
 */
package org.imsglobal.caliper.entities;

/**
 * @author pnayak
 * 
 */
public class Organization extends CaliperEntity {

	/**
	 * 
	 */
	public Organization() {
		super();
		setType("http://purl.imsglobal.org/ctx/caliper/v1/Organization");
	}

	/**
	 * @param parentOrg
	 */
	public Organization(String id, Organization parentOrg) {
		setType("http://purl.imsglobal.org/ctx/caliper/v1/Organization");
		this.parentOrg = parentOrg;
		this.id = id;
	}

	private String title;
	private Organization parentOrg;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
