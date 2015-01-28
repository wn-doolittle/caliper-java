package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.Session;
import org.imsglobal.caliper.profiles.SessionProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class SessionLoginEventTest {
    private LearningContext learningContext;
    private SoftwareApplication edApp;
    private String key;
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
        learningContext = TestUtils.buildReadiumLearningContext();

        // edApp object
        edApp = learningContext.getEdApp();

        // Action
        key = SessionProfile.Actions.LOGGEDIN.key();

        // Build target
        target = TestUtils.buildEpubSubChap431();

        // Build generated
        generated = TestUtils.buildSession();

        // Build event
        event = TestUtils.buildEpubLoginEvent(learningContext, edApp, key, target, generated);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if loggedIn event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperSessionLoginEvent.json"), asJson(event));
    }
}