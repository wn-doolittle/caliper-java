package org.imsglobal.caliper.validators.events;

import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.validators.CaliperEventValidator;
import org.imsglobal.caliper.validators.ValidatorResult;

public class EventValidatorContext<T extends Event> {
    private CaliperEventValidator<T> validator;

    /**
     * Constructor.  Configure with a concrete validator object.
     * @param validator
     */
    public EventValidatorContext(CaliperEventValidator<T> validator) {
        this.validator = validator;
    }

    /**
     * Validate the event.
     * @param event
     */
    public ValidatorResult validate(T event) {
        return this.validator.validate(event);
    }
}
