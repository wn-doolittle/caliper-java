package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Organization;
import org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication;
import org.joda.time.DateTime;

public interface Event {

    public enum Context {
        ANNOTATION("http://purl.imsglobal.org/ctx/caliper/v1/AnnotationEvent"),
        ASSESSMENT("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentEvent"),
        ASSESSMENT_ITEM("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentItemEvent"),
        ASSIGNABLE("http://purl.imsglobal.org/ctx/caliper/v1/AssignableEvent"),
        EVENT("http://purl.imsglobal.org/ctx/caliper/v1/Event"),
        MEDIA("http://purl.imsglobal.org/ctx/caliper/v1/MediaEvent"),
        NAVIGATION("http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent"),
        OUTCOME("http://purl.imsglobal.org/ctx/caliper/v1/OutcomeEvent"),
        READING("http://purl.imsglobal.org/ctx/caliper/v1/ReadingEvent"),
        SESSION("http://purl.imsglobal.org/ctx/caliper/v1/SessionEvent"),
        VIEW("http://purl.imsglobal.org/ctx/caliper/v1/ViewEvent");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Context(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    public enum Type {
        ANNOTATION("http://purl.imsglobal.org/caliper/v1/AnnotationEvent"),
        ASSESSMENT("http://purl.imsglobal.org/caliper/v1/AssessmentEvent"),
        ASSESSMENT_ITEM("http://purl.imsglobal.org/caliper/v1/AssessmentItemEvent"),
        ASSIGNABLE("http://purl.imsglobal.org/caliper/v1/AssignableEvent"),
        EVENT("http://purl.imsglobal.org/caliper/v1/Event"),
        MEDIA("http://purl.imsglobal.org/caliper/v1/MediaEvent"),
        NAVIGATION("http://purl.imsglobal.org/caliper/v1/NavigationEvent"),
        OUTCOME("http://purl.imsglobal.org/caliper/v1/OutcomeEvent"),
        READING("http://purl.imsglobal.org/caliper/v1/ReadingEvent"),
        SESSION("http://purl.imsglobal.org/caliper/v1/SessionEvent"),
        VIEW("http://purl.imsglobal.org/caliper/v1/ViewEvent");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Type(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    /**
     * @return the context
     */
    String getContext();

    /**
     * @return the type
     */
    String getType();

    /**
     * @return the edApp
     */

    SoftwareApplication getEdApp();

    /**
     * @return the lisOrganization
     */
    Organization getLisOrganization();

    /**
     * @return the actor
     */
    Agent getActor();

    /**
     * @return the action
     */
    String getAction();

    /**
     * @return the object
     */
    Object getObject();

    /**
     * @return the target
     */
    Targetable getTarget();

    /**
     * @return generated
     */
    Generatable getGenerated();

    /**
     * @return the startedAt time
     */
    DateTime getStartedAtTime();

    /**
     * @return endedAt time
     */
    DateTime getEndedAtTime();

    /*;
     * @return the duration
     */
    String getDuration();
}