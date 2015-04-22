package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestAgentEntities;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestEpubEntities;
import org.imsglobal.caliper.TestLisEntities;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.entities.reading.WebPage;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class NavigationEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubVolume object;
    private Frame target;
    private DigitalResource fromResource;
    private EpubSubChapter ePub;
    private NavigationEvent event;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateModified = TestDates.getDefaultDateModified();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(NavigationEventTest.class);

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
        object = TestEpubEntities.buildEpubVolume43();

        // Build previous location
        fromResource = WebPage.builder()
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101/index.html")
            .name("American Revolution 101 Landing Page")
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .version("1.0")
            .build();

        // Build target frame
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

        // Build event
        event = buildEvent(Action.NAVIGATED_TO);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Navigation event is serialized to JSON with expected values",
            jsonFixture("fixtures/caliperNavigationEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void navigationEventRejectsViewedAction() {
        buildEvent(Action.VIEWED);
    }

    /**
     * Build Navigation event
     * @param action
     * @return event
     */
    private NavigationEvent buildEvent(Action action) {
        return NavigationEvent.builder()
            .edApp(learningContext.getEdApp())
            .group(learningContext.getGroup())
            .actor((Person) learningContext.getAgent())
            .action(action)
            .object(object)
            .target(target)
            .fromResource(fromResource)
            .startedAtTime(dateStarted)
            .build();
    }
}