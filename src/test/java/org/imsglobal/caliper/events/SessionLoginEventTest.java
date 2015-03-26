package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.session.Session;
import org.imsglobal.caliper.profiles.Profile.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class SessionLoginEventTest extends EventTest {
    private LearningContext learningContext;
    private SoftwareApplication edApp;
    private Action key;
    private EpubSubChapter target;
    private Session generated;
    private SessionEvent event;
    private static final Logger log = LoggerFactory.getLogger(SessionLoginEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        // Action
        key = Action.LOGGED_IN;

        // Build target
        target = TestUtils.buildEpubSubChap431();

        // Build generated
        generated = TestUtils.buildSessionStart();

        // Build event
        event = TestUtils.buildEpubLoginEvent(learningContext, key, target, generated);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if loggedIn event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperSessionLoginEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void sessionEventRejectsSearchedAction() {
        TestUtils.buildEpubLoginEvent(learningContext, Action.SEARCHED, target, generated);
    }

}