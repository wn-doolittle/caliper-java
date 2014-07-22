/**
 * 
 */
package org.imsglobal.caliper.entities;

/**
 * @author pnayak
 * 
 */
public class LISOrganization extends CaliperEntity implements CaliperAgent {

	/**
	 * 
	 */
	public LISOrganization() {
		super();
		setType("http://purl.imsglobal.org/caliper/v1/LISOrganization");
	}

	/**
	 * @param parentOrg
	 */
	public LISOrganization(String id, LISOrganization parentOrg) {
		setType("http://purl.imsglobal.org/ctx/caliper/v1/LISOrganization");
		this.parentOrg = parentOrg;
		this.id = id;
	}

	private String title;
	private LISOrganization parentOrg;

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
