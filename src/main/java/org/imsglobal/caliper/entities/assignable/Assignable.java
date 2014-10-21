package org.imsglobal.caliper.entities.assignable;

/**
 * Representation of Assignable from corresponding metric profile
 */
public interface Assignable {

	long getDateCreated();

	long getDatePublished();

	long getDateToStartOn();

	long getDateToActivate();

	long getDateToShow();

	long getDateToSubmit();

	int getMaxAttempts();

	int getMaxSubmits();
}
