package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.response.Response;

import javax.annotation.Nonnull;

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
    public static ValidatorResult validate(@Nonnull Response response) {
        String context = "Response ";
        ValidatorResult result = new ValidatorResult();

        if (!(Response.Type.matchTypeURI(response.getType()))) {
            result.errorMessage().appendText(context + EventValidator.Conformance.TYPE_ERROR.violation());
        }

        ValidatorResult startTimeValidator;
        startTimeValidator = StartTimeValidator.validate(response.getStartedAtTime(), response.getEndedAtTime(), context);
        if (!startTimeValidator.isValid()) {
            result.errorMessage().appendText(startTimeValidator.errorMessage().toString());
        }

        ValidatorResult durationValidator = DurationValidator.validate(response.getStartedAtTime(),
                response.getEndedAtTime(), response.getDuration(), context);
        if (!durationValidator.isValid()) {
            result.errorMessage().appendText(durationValidator.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        }

        return result;
    }
}