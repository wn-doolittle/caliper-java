package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.SoftwareApplication;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;
import org.imsglobal.caliper.profiles.Profile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssessmentOutcomeEventTest extends EventTest {

    private LearningContext learningContext;
    private SoftwareApplication scoredBy;
    private Assessment assessment;
    private Attempt attempt;
    private Result result;
    private OutcomeEvent event;
    private static final Logger log = LoggerFactory.getLogger(AssessmentOutcomeEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the student learning context (attempt)
        learningContext = TestUtils.buildAssessmentStudentLearningContext();

        // Build scoredBy (edApp)
        scoredBy = TestUtils.buildAssessmentTool();

        // Build assessment
        assessment = TestUtils.buildAssessment();

        // Build attempt
        attempt = TestUtils.buildAssessmentAttempt(learningContext, assessment);

        // Build scoredBy (edApp)
        scoredBy = TestUtils.buildAssessmentTool();

        // Build result
        result = TestUtils.buildAssessmentResult(attempt, scoredBy);

        // Build Outcome Event
        event = TestUtils.buildAssessmentOutcomeEvent(learningContext, attempt, Profile.Action.GRADED, result);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Outcome event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssessmentOutcomeEvent.json"), serialize(event));
    }

    @Test(expected=IllegalStateException.class)
    public void outcomeEventRejectsSearchedAction(){
        TestUtils.buildAssessmentOutcomeEvent(learningContext, attempt, Profile.Action.SEARCHED, result);
    }
}