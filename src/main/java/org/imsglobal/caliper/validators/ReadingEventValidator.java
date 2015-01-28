package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;
import org.imsglobal.caliper.entities.schemadotorg.Thing;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.profiles.ReadingProfile;

public class ReadingEventValidator implements EventValidator {

    private String key;

    /**
     * Constructor
     */
    public ReadingEventValidator(String key) {
        this.key = key;
    }

    /**
     * Validate assessment event properties.
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
     * <dd>required: reading.*</dd>
     * <dt>object</dt>
     * <dd>required: DigitalResource</dd>
     * <dt>target</dt>
     * <dd>required: reading.viewed (CreativeWork), reading.searched (Thing)</dd>
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
    public ValidatorResult validate(Event event) {
        String context = "ReadingEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.READING.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.READING.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (key.equals(ReadingProfile.Actions.SEARCHED.key())) {
            if (!ValidatorUtils.isOfType(event.getTarget(), Thing.class)) {
                result.errorMessage().appendText(context + Conformance.TARGET_NOT_THING.violation());
            }
        } else if (key.equals(ReadingProfile.Actions.VIEWED.key())) {
            if (!ValidatorUtils.isOfType(event.getTarget(), CreativeWork.class)) {
                result.errorMessage().appendText(context + Conformance.TARGET_NOT_CREATIVEWORK.violation());
            }
        }

        if (ValidatorUtils.checkStartedAtTime(event.getStartedAtTime())) {
            if (!ValidatorUtils.checkStartEndTimes(event.getStartedAtTime(), event.getEndedAtTime())) {
                result.errorMessage().appendText(context + Conformance.TIME_ERROR.violation());
            }
        } else {
            result.errorMessage().appendText(context + Conformance.STARTEDATTIME_IS_NULL.violation());
        }

        if (!ValidatorUtils.checkDuration(event.getDuration())) {
            result.errorMessage().appendText(context + Conformance.DURATION_INVALID.violation());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Reading profile conformance:");
        }

        return result;
    }
}