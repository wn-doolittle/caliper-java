package org.imsglobal.caliper.validators;

import org.joda.time.DateTime;

public class EndTimeValidator {

    /**
     * Constructor
     */
    public EndTimeValidator() {

    }

    public static ValidatorResult validate(DateTime startedAtTime, DateTime endedAtTime, String context) {
        ValidatorResult result = new ValidatorResult();

        if ((endedAtTime != null)) {
            if (startedAtTime != null && !ValidatorUtils.checkStartEndTimes(startedAtTime, endedAtTime)) {
                result.errorMessage().appendText(context + EventValidator.Conformance.TIME_ERROR.violation());
            }
        } else {
            result.errorMessage().appendText(context + EventValidator.Conformance.ENDEDATTIME_IS_NULL.violation());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        }

        return result;
    }
}