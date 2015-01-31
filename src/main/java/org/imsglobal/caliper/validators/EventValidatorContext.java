package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.events.Event;

public class EventValidatorContext {
    private EventValidator validator;

    /**
     * Configure with a concrete validator object.
     * @param validator
     */
    public EventValidatorContext(EventValidator validator) {
        this.validator = validator;
    }

    /**
     * Do the validation.
     * @param event
     */
    public ValidatorResult validate(Event event) {
        return this.validator.validate(event);
    }
}