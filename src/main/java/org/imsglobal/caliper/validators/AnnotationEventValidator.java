package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.annotation.Annotation;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.events.Event;

import javax.annotation.Nonnull;

public class AnnotationEventValidator implements EventValidator {

    /**
     * Constructor
     */
    public AnnotationEventValidator() {

    }

    /**
     * Validate annotation event properties.
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
     * <dd>required: annotation.*</dd>
     * <dt>object</dt>
     * <dd>required: Annotation</dd>
     * <dt>target</dt>
     * <dd>required: DigitalResource.</dd>
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
        String context = "AnnotationEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.ANNOTATION.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.ANNOTATION.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (!ValidatorUtils.isOfType(event.getActor(), Person.class)) {
            result.errorMessage().appendText(context + Conformance.ACTOR_NOT_PERSON.violation());
        }

        if (!ValidatorUtils.isOfType(event.getObject(), Annotation.class)) {
            result.errorMessage().appendText(context + Conformance.OBJECT_NOT_ANNOTATION.violation());
        }

        if (!ValidatorUtils.isOfType(event.getTarget(), DigitalResource.class)) {
            result.errorMessage().appendText(context + Conformance.TARGET_NOT_DIGITALRESOURCE.violation());
        }

        ValidatorResult startTimeValidator = StartTimeValidator.validate(event.getStartedAtTime(),
                event.getEndedAtTime(), context);
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
            result.errorMessage().endMessage("Caliper Annotation profile conformance:");
        }

        return result;
    }
}
