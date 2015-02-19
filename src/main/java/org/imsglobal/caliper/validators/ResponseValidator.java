package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.response.Response;

public class ResponseValidator {


    /**
     * Constructor
     */
    public ResponseValidator() {

    }

    /**
     * Validate response properties.
     * <p/>
     * <h3>Properties</h3>
     * <dl>
     * <dt>id</dt>
     * <dd>required</dd>
     * <dt>type</dt>
     * <dd>required</dd>
     * <dt>name</dt>
     * <dd>optional</dd>
     * <dt>lastModifiedDate</dt>
     * <dd>optional</dd>
     * <dt>assignableId</dt>
     * <dd>required</dd>
     * <dt>actorId</dt>
     * <dd>required</dd>
     * <dt>value(s)</dt>
     * <dd>optional</dd>
     * <dt>startedAtTime</dt>
     * <dd>required</dd>
     * <dt>endedAtTime</dt>
     * <dd>optional</dd>
     * <dt>duration</dt>
     * <dd>optional</dd>
     * </dl>
     *
     * @param response
     * @return conformance violations message.
     */
    public static ValidatorResult validate(Response response) {
        String context = "Response ";
        ValidatorResult result = new ValidatorResult();

        if (!(Response.Type.matchTypeURI(response.getType()))) {
            result.errorMessage().appendText(context + EventValidator.Conformance.TYPE_ERROR.violation());
        }

        if (ValidatorUtils.checkStartedAtTime(response.getStartedAtTime())) {
            if (!ValidatorUtils.checkStartEndTimes(response.getStartedAtTime(), response.getEndedAtTime())) {
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