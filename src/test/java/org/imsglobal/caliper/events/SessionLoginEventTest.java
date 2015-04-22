package org.imsglobal.caliper.events;

import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.TestAgentEntities;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestEpubEntities;
import org.imsglobal.caliper.TestLisEntities;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.entities.session.Session;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class SessionLoginEventTest extends EventTest {
    private LearningContext learningContext;
    private SoftwareApplication object;
    private EpubSubChapter ePub;
    private Frame target;
    private Session generated;
    private SessionEvent event;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateModified = TestDates.getDefaultDateModified();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(SessionLoginEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = LearningContext.builder()
            .edApp(TestAgentEntities.buildReadiumViewerApp())
            .group(TestLisEntities.buildGroup())
            .agent(TestAgentEntities.buildStudent554433())
            .build();

        // Build object
        object = learningContext.getEdApp();

        // Build target
        ePub = TestEpubEntities.buildEpubSubChap431();
        target = Frame.builder()
            .id(ePub.getId())
            .name(ePub.getName())
            .isPartOf(ePub.getIsPartOf())
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .version(ePub.getVersion())
            .index(1)
            .build();

        // Build generated
        generated = Session.builder()
            .id("https://github.com/readium/session-123456789")
            .name("session-123456789")
            .actor(learningContext.getAgent())
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .startedAtTime(dateStarted)
            .build();

        // Build event
        event = buildEvent(Action.LOGGED_IN);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if loggedIn event is serialized to JSON with expected values",
            jsonFixture("fixtures/caliperSessionLoginEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void sessionEventRejectsSearchedAction() {
        buildEvent(Action.SEARCHED);
    }

    /**
     * Build Session login event.
     * @param action
     * @return event
     */
    private SessionEvent buildEvent(Action action) {
        return SessionEvent.builder()
            .edApp(learningContext.getEdApp())
            .group(learningContext.getGroup())
            .actor((Person)learningContext.getAgent())
            .action(action)
            .object(learningContext.getEdApp())
            .target(target)
            .generated(generated)
            .startedAtTime(dateStarted)
            .build();
    }
}