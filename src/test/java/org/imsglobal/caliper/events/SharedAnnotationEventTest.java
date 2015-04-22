package org.imsglobal.caliper.events;

import com.google.common.collect.Lists;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.TestAgentEntities;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestEpubEntities;
import org.imsglobal.caliper.TestLisEntities;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.annotation.SharedAnnotation;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.Frame;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class SharedAnnotationEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubSubChapter ePub;
    private Frame object;
    private SharedAnnotation generated;
    private AnnotationEvent event;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateModified = TestDates.getDefaultDateModified();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(SharedAnnotationEventTest.class);

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

        // Build object frame
        ePub = TestEpubEntities.buildEpubSubChap433();
        object = Frame.builder()
            .id(ePub.getId())
            .name(ePub.getName())
            .isPartOf(ePub.getIsPartOf())
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .version(ePub.getVersion())
            .index(3)
            .build();

        // Add shared with agents
        List<Agent> shared = Lists.newArrayList();
        shared.add(Person.builder()
            .id("https://some-university.edu/students/657585")
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .build());
        shared.add(Person.builder()
            .id("https://some-university.edu/students/667788")
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .build());

        // Build Shared Annotation
        generated = SharedAnnotation.builder()
            .id("https://someEduApp.edu/shared/9999")
            .annotated(object)
            .withAgents(shared)
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .build();

        // Build event
        event = buildEvent(Action.SHARED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Shared Annotation event is serialized to JSON with expected values",
            jsonFixture("fixtures/caliperSharedAnnotationEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void annotationEventRejectsGradedAction() {
        buildEvent(Action.GRADED);
    }

    /**
     * Build Annotation event.
     * @param action
     * @return event
     */
    private AnnotationEvent buildEvent(Action action) {
        return AnnotationEvent.builder()
            .edApp(learningContext.getEdApp())
            .group(learningContext.getGroup())
            .actor((Person) learningContext.getAgent())
            .action(action)
            .object(object)
            .generated(generated)
            .startedAtTime(dateStarted)
            .build();
    }
}