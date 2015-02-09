package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Session;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.profiles.SessionProfile;

public class SessionEventValidator implements EventValidator {

    private String key;
    /**
     * Constructor
     */
    public SessionEventValidator(String key) {
        this.key = key;
    }

    /**
     * Validate session event properties.
     * <p/>
     * <h3>Login properties</h3>
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
     * <h3> Logout properties</h3>
     * <dl>
     *   <dt>context</dt>
     *   <dd>required</dd>
     *   <dt>type</dt>
     *   <dd>required</dd>
     *   <dt>edApp</dt>
     *   <dd>optional</dd>
     *   <dt>group</dt>
     *   <dd>optional</dd>
     *   <dt>actor</dt>
     *   <dd>required: Person</dd>
     *   <dt>action</dt>
     *   <dd>required: session.loggedOut</dd>
     *   <dt>object</dt>
     *   <dd>required: SoftwareApplication</dd>
     *   <dt>target</dt>
     *   <dd>required: Session</dd>
     *   <dt>generated</dt>
     *   <dd>A generated object should not be specified.</dd>
     *   <dt>startedAtTime</dt>
     *   <dd>required</dd>
     *   <dt>endedAtTime</dt>
     *   <dd>optional</dd>
     *   <dt>duration</dt>
     *   <dd>optional</dd>
     * </dl>
     *
     * <h3>Timeout properties</h3>
     * <dl>
     *   <dt>context</dt>
     *   <dd>required</dd>
     *   <dt>type</dt>
     *   <dd>required</dd>
     *   <dt>edApp</dt>
     *   <dd>optional</dd>
     *   <dt>group</dt>
     *   <dd>optional</dd>
     *   <dt>actor</dt>
     *   <dd>required: SoftwareApplication</dd>
     *   <dt>action</dt>
     *   <dd>required: session.timedOut</dd>
     *   <dt>object</dt>
     *   <dd>required: SoftwareApplication</dd>
     *   <dt>target</dt>
     *   <dd>required: Session</dd>
     *   <dt>generated</dt>
     *   <dd>A generated object should not be specified.</dd>
     *   <dt>startedAtTime</dt>
     *   <dd>required</dd>
     *   <dt>endedAtTime</dt>
     *   <dd>optional</dd>
     *   <dt>duration</dt>
     *   <dd>optional</dd>
     * </dl>
     *
     * @param event
     * @return conformance violations message.
     */
    public ValidatorResult validate(Event event) {
        String context = "SessionLoginEvent ";
        ValidatorResult result = new ValidatorResult();

        if (!event.getContext().equals(Event.Context.SESSION.uri())) {
            result.errorMessage().appendText(context + Conformance.CONTEXT_ERROR.violation());
        }

        if (!event.getType().equals(Event.Type.SESSION.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (key.equals(SessionProfile.Actions.TIMEDOUT.key())) {
            if (!ValidatorUtils.isOfType(event.getActor(), SoftwareApplication.class)) {
                result.errorMessage().appendText(context + Conformance.ACTOR_NOT_SOFTWAREAPP.violation());
            }
        } else {
            if (!ValidatorUtils.isOfType(event.getActor(), Person.class)) {
                result.errorMessage().appendText(context + Conformance.ACTOR_NOT_PERSON.violation());
            }
        }

        if (key.equals(SessionProfile.Actions.LOGGEDIN.key())) {
            if (!ValidatorUtils.isOfType(event.getTarget(), DigitalResource.class)) {
                result.errorMessage().appendText(context + Conformance.TARGET_NOT_DIGITALRESOURCE.violation());
            }
        } else {
            ValidatorResult sessionValidatorResult = SessionValidator.validate(((Session) event.getTarget()), key);
            if (!sessionValidatorResult.isValid()) {
                result.errorMessage().appendText(sessionValidatorResult.errorMessage().toString());
            }
        }

        if (!ValidatorUtils.checkStartedAtTime(event.getStartedAtTime())) {
            result.errorMessage().appendText(context + Conformance.STARTEDATTIME_IS_NULL.violation());
        }

        if (key.equals(SessionProfile.Actions.LOGGEDIN.key())) {
//            if (event.getEndedAtTime() > 0) {
//                result.errorMessage().appendText(context + Conformance.ENDEDATTIME_SET.violation());
//            }
        } else {
            if (ValidatorUtils.checkEndedAtTime(event.getEndedAtTime())) {
                if (!ValidatorUtils.checkStartEndTimes(event.getStartedAtTime(), event.getEndedAtTime())) {
                    result.errorMessage().appendText(context + Conformance.TIME_ERROR.violation());
                }
            } else {
                result.errorMessage().appendText(context + Conformance.ENDEDATTIME_IS_NULL.violation());
            }
        }

        if (!ValidatorUtils.checkDuration(event.getDuration())) {
            result.errorMessage().appendText(context + Conformance.DURATION_INVALID.violation());
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        } else {
            result.errorMessage().endMessage("Caliper Session profile conformance:");
        }

        return result;
    }
}