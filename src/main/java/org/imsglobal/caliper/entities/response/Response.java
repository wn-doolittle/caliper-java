package org.imsglobal.caliper.entities.response;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.joda.time.DateTime;

public interface Response extends Entity, Generatable {

    DigitalResource getAssignable();

    Agent getActor();

    Attempt getAttempt();

    DateTime getStartedAtTime();

    DateTime getEndedAtTime();

    String getDuration();
}
