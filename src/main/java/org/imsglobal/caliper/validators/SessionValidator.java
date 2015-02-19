package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.Session;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.profiles.SessionProfile;
import org.imsglobal.caliper.validators.EventValidator.Conformance;

import javax.annotation.Nonnull;

public class SessionValidator {

    /**
     * Constructor
     */
    public SessionValidator() {

    }

    /**
     * Validate attempt properties.
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
     * <dd>required</dd>
     * <dt>actor: Person</dt>
     * <dd>required</dd>
     * <dt>startedAtTime</dt>
     * <dd>required</dd>
     * <dt>endedAtTime</dt>
     * <dd>required: session.loggedOut, session.timedOut</dd>
     * <dt>duration</dt>
     * <dd>optional</dd>
     * </dl>
     *
     * @param session
     * @return conformance violations message.
     */
    public static ValidatorResult validate(@Nonnull Session session, @Nonnull String key) {
        String context = "Session ";
        ValidatorResult result = new ValidatorResult();

        if (!session.getType().equals(Entity.Type.SESSION.uri())) {
            result.errorMessage().appendText(context + Conformance.TYPE_ERROR.violation());
        }

        if (!ValidatorUtils.isOfType(session.getActor(), Person.class)) {
            result.errorMessage().appendText(context + Conformance.ACTOR_NOT_PERSON.violation());
        }

        if (key.equals(SessionProfile.Actions.LOGGEDOUT.key()) || key.equals(SessionProfile.Actions.TIMEDOUT.key())) {
            if (!ValidatorUtils.checkStartedAtTime(session.getStartedAtTime())) {
                result.errorMessage().appendText(context + Conformance.STARTEDATTIME_IS_NULL.violation());
            } else {
//                if (session.getEndedAtTime() > 0) {
//                    if (!ValidatorUtils.checkStartEndTimes(session.getStartedAtTime(), session.getEndedAtTime())) {
//                        result.errorMessage().appendText(context + Conformance.TIME_ERROR.violation());
//                    }
//                } else {
//                    result.errorMessage().appendText(context + Conformance.ENDEDATTIME_IS_NULL.violation());
//                }
            }
        } else {
            if (!ValidatorUtils.checkStartedAtTime(session.getStartedAtTime())) {
                result.errorMessage().appendText(context + Conformance.STARTEDATTIME_IS_NULL.violation());
            } else {
//                if (session.getEndedAtTime() > 0) {
//                    if (!ValidatorUtils.checkStartEndTimes(session.getStartedAtTime(), session.getEndedAtTime())) {
//                        result.errorMessage().appendText(context + Conformance.TIME_ERROR.violation());
//                    }
//                }
            }
        }

        if (result.errorMessage().length() == 0) {
            result.setIsValid(true);
        }

        return result;
    }
}