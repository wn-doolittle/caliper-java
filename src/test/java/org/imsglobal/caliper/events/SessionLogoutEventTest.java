package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.session.Session;
import org.imsglobal.caliper.actions.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class SessionLogoutEventTest extends EventTest {
    private LearningContext learningContext;
    private SoftwareApplication edApp;
    private Session target;
    private SessionEvent event;
    private static final Logger log = LoggerFactory.getLogger(SessionLogoutEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        // Build target
        target = TestUtils.buildSessionEnd();

        // Build event
        event = TestUtils.buildEpubLogoutEvent(learningContext, Action.LOGGED_OUT, target);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if loggedOut event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperSessionLogoutEvent.json"), serialize(event));
    }
}