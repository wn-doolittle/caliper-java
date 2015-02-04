package org.imsglobal.caliper.entities.assignable;

import java.util.Date;

/**
 * Representation of Assignable from corresponding metric profile
 */
public interface Assignable {

    Date getDateToStartOn();

    Date getDateToActivate();

    Date getDateToShow();

    Date getDateToSubmit();

    int getMaxAttempts();

    int getMaxSubmits();
}