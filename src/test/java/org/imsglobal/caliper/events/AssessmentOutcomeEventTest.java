package org.imsglobal.caliper.events;

import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.TestAgentEntities;
import org.imsglobal.caliper.TestAssessmentEntities;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestLisEntities;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssessmentOutcomeEventTest extends EventTest {

    private LearningContext learningContext;
    private SoftwareApplication scoredBy;
    private Assessment assessment;
    private Attempt object;
    private Result generated;
    private OutcomeEvent event;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(AssessmentOutcomeEventTest.class);

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
        assessment = TestAssessmentEntities.buildAssessment();

        // Build attempt
        object = Attempt.builder()
            .id(assessment.getId() + "/attempt1")
            .assignable(assessment)
            .actor(((Person) learningContext.getAgent()))
            .count(1)
            .dateCreated(dateCreated)
            .startedAtTime(dateStarted)
            .build();

        // Build result
        generated = Result.builder()
            .id(object.getId() + "/result")
            .assignable(assessment)
            .actor(learningContext.getAgent())
            .dateCreated(dateCreated)
            .normalScore(3.0d)
            .penaltyScore(0.0d)
            .extraCreditScore(0.0d)
            .totalScore(3.0d)
            .curvedTotalScore(3.0d)
            .curveFactor(0.0d)
            .comment("Well done.")
            .scoredBy((SoftwareApplication) learningContext.getEdApp())
            .build();

        // Build Outcome Event
        event = buildEvent(Action.GRADED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Outcome event is serialized to JSON with expected values",
            jsonFixture("fixtures/caliperAssessmentOutcomeEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void outcomeEventRejectsHidAction() {
        buildEvent(Action.HID);
    }

    /**
     * Build Outcome event.
     * @param action
     * @return event
     */
    private OutcomeEvent buildEvent(Action action) {
        return OutcomeEvent.builder()
            .edApp(learningContext.getEdApp())
            .group(learningContext.getGroup())
            .actor(learningContext.getAgent())
            .action(action)
            .object(object)
            .generated(generated)
            .startedAtTime(dateStarted)
            .build();
    }
}