package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.Session;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.profiles.Profile;

public class SessionLoginEventValidator implements EventValidator {

    /**
     * Constructor
     */
    public SessionLoginEventValidator() {

    }

    /**
     * Validate session login event properties.
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
     * <dd>required: session.loggedIn</dd>
     * <dt>object</dt>
     * <dd>required: SoftwareApplication</dd>
     * <dt>target</dt>
     * <dd>required: DigitalResource</dd>
     * <dt>generated</dt>
     * <dd>required: Session</dd>
     * <dt>startedAtTime</dt>
     * <dd>required</dd>
     * <dt>endedAtTime</dt>
     * <dd>optional: but should not be set.</dd>
     * <dt>duration</dt>
     * <dd>optional: but should not be set.</dd>
     * </dl>
     *
     * @param event
     * @return conformance violations message.
     */
    @Override
    public String validate(Event event) {
        String context = "session";
        ErrorMessage message = new ErrorMessage();

        if (!event.getContext().equals(Event.Context.SESSION.uri())) {
            message.appendText(context + " " + Profile.Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.SESSION.uri())) {
            message.appendText(context + " " + Profile.Conformance.TYPE_ERROR.violation());
        }

        if (!PropertyTypeCheck.isAgentPerson(event.getActor())) {
            message.appendText(Profile.Conformance.ACTOR_NOT_PERSON.violation());
        }

        if (!PropertyTypeCheck.isObjectSoftwareApplication(event.getObject())) {
            message.appendText(Profile.Conformance.OBJECT_NOT_SOFTWAREAPP.violation());
        }

        if (!PropertyTypeCheck.isTargetableDigitalResource(event.getTarget())) {
            message.appendText(Profile.Conformance.TARGET_NOT_DIGITALRESOURCE.violation());
        }

        if (PropertyTypeCheck.isGeneratableSession(event.getGenerated())) {
            if (((Session) event.getGenerated()).getStartedAtTime() <= 0) {
                message.appendText(context + " " + Profile.Conformance.STARTEDATTIME_IS_NULL.violation());
            } else {
                if (((Session) event.getGenerated()).getEndedAtTime() > 0) {
                    message.appendText(context + " " + Profile.Conformance.ENDEDATTIME_SET.violation());
                }
            }
        } else {
            message.appendText(Profile.Conformance.GENERATED_NOT_SESSION.violation());
        }

        if (!TimeCheck.checkStartedAtTime(event.getStartedAtTime())) {
            message.appendText(Profile.Conformance.STARTEDATTIME_IS_NULL.violation());
        }

        if (!TimeCheck.checkEndedAtTime(event.getStartedAtTime(), event.getEndedAtTime())) {
            message.appendText(Profile.Conformance.TIME_ERROR.violation());
        }

        if (!TimeCheck.checkDuration(event.getDuration())) {
            message.appendText(Profile.Conformance.DURATION_INVALID.violation());
        }

        if (message.length() > 0) {
            message.endMessage("Caliper Session profile login event conformance: ");
        }

        return message.toString();
    }
}