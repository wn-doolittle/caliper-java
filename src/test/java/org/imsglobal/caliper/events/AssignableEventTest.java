package org.imsglobal.caliper.events;

import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.TestAgentEntities;
import org.imsglobal.caliper.TestAssessmentEntities;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestLisEntities;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssignableEventTest extends EventTest {
    private LearningContext learningContext;
    private Assessment object;
    private Attempt generated;
    private AssignableEvent event;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(AssignableEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = LearningContext.builder()
            .edApp(TestAgentEntities.buildAssessmentApp())
            .group(TestLisEntities.buildGroup())
            .agent(TestAgentEntities.buildStudent554433())
            .build();

        // Build assessment
        object = TestAssessmentEntities.buildAssessment();

        // Build generated attempt
        generated = Attempt.builder()
            .id(object.getId() + "/attempt1")
            .assignable(object)
            .actor(((Person) learningContext.getAgent()))
            .count(1)
            .dateCreated(dateCreated)
            .startedAtTime(dateStarted)
            .build();

        // Build event
        event = buildEvent(Action.ACTIVATED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Assignable event is serialized to JSON with expected values",
            jsonFixture("fixtures/caliperAssignableEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void assignableEventRejectsSearchedAction() {
        buildEvent(Action.SEARCHED);
    }

    /**
     * Build Assignable event.
     * @param action
     * @return event
     */
    private AssignableEvent buildEvent(Action action) {
        return AssignableEvent.builder()
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