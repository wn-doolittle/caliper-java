/**
 * 
 */
package org.imsglobal.caliper.entities.assignable;

/**
 * @author pnayak
 * 
 *         Representation of Assignable from correspoding metric profile
 */
public interface Assignable {

	public long getDateCreated();

	public long getDatePublished();

	public long getDateToStartOn();

	public long getDateToActivate();

	public long getDateToShow();

	public long getDateToSubmit();

	public int getMaxAttempts();

	public int getMaxSubmits();
}
