package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.session.Session;

public class TestSessionEntities {

    /**
     * Constructor
     */
    public TestSessionEntities() {

    }

    /**
     * Create a FederatedSession with URI identifier provided by an LTI Tool Consumer.
     * @return federatedSession
     */
    public static final Session buildFederatedSession(Agent actor) {
        return Session.builder()
            .id("https://example.edu/lms/federatedSession/123456789")
            .actor(actor)
            .dateCreated(TestDates.getDefaultDateCreated())
            .startedAtTime(TestDates.getDefaultStartedAtTime())
            .build();
    }
}