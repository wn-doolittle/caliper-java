package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.entities.schemadotorg.MediaObject;
import org.imsglobal.caliper.events.Event;

import javax.annotation.Nonnull;

public class MediaEventValidator implements EventValidator {
    /**
     * Constructor
     */
    public MediaEventValidator() {

    }

    /**
     * Validate media event properties.
     * <p/>
     * <h3>Properties</h3>
     * <dl>
     * <dt>context</dt>
     * <dd>required</dd>
     * <dt>type</dt>
     * <dd>required</dd>
     * <dt>edApp</dt>
     * <dd>optional</dd>
     * <dt>group</dt>
     * <dd>optional</dd>
     * <dt>actor</dt>
     * <dd>required: Person</dd>
     * <dt>action</dt>
     * <dd>required: media.*</dd>
     * <dt>object</dt>
     * <dd>required: MediaObject</dd>
     * <dt>target</dt>
     * <dd>optional</dd>
     * <dt>generated</dt>
     * <dd>optional</dd>
     * <dt>startedAtTime</dt>
     * <dd>required</dd>
     * <dt>endedAtTime</dt>
     * <dd>optional</dd>
     * <dt>duration</dt>
     * <dd>optional</dd>
     * </dl>
     *
     * @param event
     * @return conformance violations message.
     */
    public ValidatorResult validate(@Nonnull Event event) {
        String context = "MediaEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.MEDIA.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.MEDIA.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (!ValidatorUtils.isOfType(event.getActor(), Person.class)) {
            result.errorMessage().appendText(context + Conformance.ACTOR_NOT_PERSON.violation());
        }

        if (!ValidatorUtils.isOfType(event.getObject(), MediaObject.class)) {
            result.errorMessage().appendText(context + Conformance.OBJECT_NOT_MEDIA.violation());
        }

        ValidatorResult startTimeValidator;
        startTimeValidator = StartTimeValidator.validate(event.getStartedAtTime(), event.getEndedAtTime(), context);
        if (!startTimeValidator.isValid()) {
            result.errorMessage().appendText(startTimeValidator.errorMessage().toString());
        }

        ValidatorResult durationValidator = DurationValidator.validate(event.getStartedAtTime(),
                event.getEndedAtTime(), event.getDuration(), context);
        if (!durationValidator.isValid()) {
            result.errorMessage().appendText(durationValidator.errorMessage().toString());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Media profile conformance:");
        }

        return result;
    }
}