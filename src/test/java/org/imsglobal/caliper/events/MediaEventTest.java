package org.imsglobal.caliper.events;

import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.LearningObjective;
import org.imsglobal.caliper.TestAgentEntities;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestLisEntities;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.entities.media.VideoObject;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class MediaEventTest extends EventTest {

    private LearningContext learningContext;
    private VideoObject object;
    private MediaEvent event;
    private MediaLocation target;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateModified = TestDates.getDefaultDateModified();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(MediaEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = LearningContext.builder()
            .edApp(TestAgentEntities.buildMediaPlayerApp())
            .group(TestLisEntities.buildGroup())
            .agent(TestAgentEntities.buildStudent554433())
            .build();

        // Build video
        object = VideoObject.builder()
            .id("https://com.sat/super-media-tool/video/video1")
            .name("American Revolution - Key Figures Video")
            .learningObjective(LearningObjective.builder()
                .id("http://americanrevolution.com/personalities/learn")
                .dateCreated(dateCreated)
                .build())
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .version("1.0")
            .duration(1420)
            .build();

        // Build media location
        target = MediaLocation.builder()
            .id(object.getId())
            .dateCreated(dateCreated)
            .version(object.getVersion())
            .currentTime(710)
            .build();

        // Build event
        event = buildEvent(Action.PAUSED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Media event is serialized to JSON with expected values",
            jsonFixture("fixtures/caliperMediaEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void mediaEventRejectsSubmittedAction() {
        buildEvent(Action.SUBMITTED);
    }

    /**
     * Build Media event.
     * @param action
     * @return event
     */
    private MediaEvent buildEvent(Action action) {
        return MediaEvent.builder()
            .edApp(learningContext.getEdApp())
            .group(learningContext.getGroup())
            .actor((Person) learningContext.getAgent())
            .action(action)
            .object(object)
            .target(target)
            .startedAtTime(dateStarted)
            .build();
    }
}