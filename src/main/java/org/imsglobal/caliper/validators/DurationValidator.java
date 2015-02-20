package org.imsglobal.caliper.validators;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class DurationValidator {

    /**
     * Constructor
     */
    public DurationValidator() {

    }

    public static ValidatorResult validate(DateTime startedAtTime, DateTime endedAtTime, String duration, String context) {
        ValidatorResult result = new ValidatorResult();
        
        if (startedAtTime != null && endedAtTime != null) {
            ValidatorResult startTimeValidator;
            startTimeValidator = StartTimeValidator.validate(startedAtTime, endedAtTime, context);
            if (startTimeValidator.isValid()) {
                if (duration != null && !duration.equals(new Interval(startedAtTime, endedAtTime).toDuration().toString())) {
                    result.errorMessage().appendText(context + EventValidator.Conformance.DURATION_MISCALCULATED.violation());
                }
            } else {
                result.errorMessage().appendText(context + EventValidator.Conformance.DURATION_ERROR.violation());
            }
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        }

        return result;
    }
}