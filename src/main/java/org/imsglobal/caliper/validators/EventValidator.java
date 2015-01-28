package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.events.Event;

public interface EventValidator {
    String validate(Event event);
}
