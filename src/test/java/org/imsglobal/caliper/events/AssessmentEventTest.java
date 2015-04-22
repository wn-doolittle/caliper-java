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
public class AssessmentEventTest extends EventTest {

    private LearningContext learningContext;
    private Assessment object;
    private Attempt generated;
    private AssessmentEvent event;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(AssessmentEventTest.class);

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

        event = buildEvent(Action.STARTED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Assessment event is serialized to JSON with expected values",
            jsonFixture("fixtures/caliperAssessmentEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void assessmentEventRejectsSearchedAction() {
        buildEvent(Action.SEARCHED);
    }

    /**
     * Build Assessment event
     * @param action
     * @return event
     */
    private AssessmentEvent buildEvent(Action action) {
        return AssessmentEvent.builder()
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