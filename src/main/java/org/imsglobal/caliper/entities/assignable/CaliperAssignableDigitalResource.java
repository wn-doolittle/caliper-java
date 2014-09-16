/**
 * 
 */
package org.imsglobal.caliper.entities.assignable;

import org.imsglobal.caliper.entities.CaliperDigitalResource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author pnayak
 * 
 */
@JsonInclude(Include.NON_NULL)
public class CaliperAssignableDigitalResource extends CaliperDigitalResource
		implements Assignable {

	private long dateCreated, datePublished, dateToActivate, dateToShow,
			dateToStartOn, dateToSubmit;
	private int maxAttempts, maxSubmits;
	private double maxScore;
	
	public CaliperAssignableDigitalResource(String id) {
		super();
		setId(id);
		setType("http://purl.imsglobal.org/caliper/v1/Assignment");
	}

	/**
	 * @return the dateCreated
	 */
	public long getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the datePublished
	 */
	public long getDatePublished() {
		return datePublished;
	}

	/**
	 * @param datePublished
	 *            the datePublished to set
	 */
	public void setDatePublished(long datePublished) {
		this.datePublished = datePublished;
	}

	/**
	 * @return the dateToActivate
	 */
	public long getDateToActivate() {
		return dateToActivate;
	}

	/**
	 * @param dateToActivate
	 *            the dateToActivate to set
	 */
	public void setDateToActivate(long dateToActivate) {
		this.dateToActivate = dateToActivate;
	}

	/**
	 * @return the dateToShow
	 */
	public long getDateToShow() {
		return dateToShow;
	}

	/**
	 * @param dateToShow
	 *            the dateToShow to set
	 */
	public void setDateToShow(long dateToShow) {
		this.dateToShow = dateToShow;
	}

	/**
	 * @return the dateToStartOn
	 */
	public long getDateToStartOn() {
		return dateToStartOn;
	}

	/**
	 * @param dateToStartOn
	 *            the dateToStartOn to set
	 */
	public void setDateToStartOn(long dateToStartOn) {
		this.dateToStartOn = dateToStartOn;
	}

	/**
	 * @return the dateToSubmit
	 */
	public long getDateToSubmit() {
		return dateToSubmit;
	}

	/**
	 * @param dateToSubmit
	 *            the dateToSubmit to set
	 */
	public void setDateToSubmit(long dateToSubmit) {
		this.dateToSubmit = dateToSubmit;
	}

	/**
	 * @return the maxAttempts
	 */
	public int getMaxAttempts() {
		return maxAttempts;
	}

	/**
	 * @param maxAttempts
	 *            the maxAttempts to set
	 */
	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	/**
	 * @return the maxSubmits
	 */
	public int getMaxSubmits() {
		return maxSubmits;
	}

	/**
	 * @param maxSubmits
	 *            the maxSubmits to set
	 */
	public void setMaxSubmits(int maxSubmits) {
		this.maxSubmits = maxSubmits;
	}

	/**
	 * @return the maxScore
	 */
	public double getMaxScore() {
		return maxScore;
	}

	/**
	 * @param maxScore
	 *            the maxScore to set
	 */
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}

}
