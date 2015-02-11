package org.imsglobal.caliper.entities.assignable;

import org.joda.time.DateTime;

/**
 * Representation of Assignable from corresponding metric profile
 */
public interface Assignable {

    DateTime getDateToStartOn();

    DateTime getDateToActivate();

    DateTime getDateToShow();

    DateTime getDateToSubmit();

    int getMaxAttempts();

    int getMaxSubmits();
}