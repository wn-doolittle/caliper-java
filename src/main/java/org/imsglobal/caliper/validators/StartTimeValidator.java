package org.imsglobal.caliper.validators;

import org.joda.time.DateTime;

public class StartTimeValidator {

    /**
     * Constructor
     */
    public StartTimeValidator() {

    }

    public static ValidatorResult validate(DateTime startedAtTime, DateTime endedAtTime, String context) {
        ValidatorResult result = new ValidatorResult();

        if ((startedAtTime != null)) {
            if (endedAtTime != null && !ValidatorUtils.checkStartEndTimes(startedAtTime, endedAtTime)) {
                result.errorMessage().appendText(context + EventValidator.Conformance.TIME_ERROR.violation());
            }
        } else {
            result.errorMessage().appendText(context + EventValidator.Conformance.STARTEDATTIME_IS_NULL.violation());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        }

        return result;
    }
}