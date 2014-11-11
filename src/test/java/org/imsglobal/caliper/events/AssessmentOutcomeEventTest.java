package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.profiles.AssessmentProfile;
import org.imsglobal.caliper.profiles.OutcomeProfile;
import org.imsglobal.caliper.entities.outcome.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssessmentOutcomeEventTest {

    private Assessment assessment;
    private AssessmentProfile assessmentProfile;
    private LearningContext learningContext;
    private OutcomeEvent event;
    private OutcomeProfile outcomeProfile;
    private Result result;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildLearningContext();

        // Build assessment
        assessment = TestUtils.buildAssessment(learningContext);

        // Build Assessment Profile
        assessmentProfile = TestUtils.buildAssessmentProfile(learningContext, assessment);

        // Start Assessment and generate attempt
        assessmentProfile = TestUtils.startAssessment(assessmentProfile);

        // Complete Assessment
        //assessmentProfile = TestUtils.completeAssessment(assessmentProfile);

        // Generate result
        result = TestUtils.buildAssessmentResult(assessmentProfile);

        // Build Outcome Profile
        outcomeProfile = TestUtils.buildAssessmentOutcomeProfile(learningContext, assessmentProfile, result);

        // Build event
        event = TestUtils.buildOutcomeEvent(outcomeProfile);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Outcome event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssessmentOutcomeEvent.json"), asJson(event));
    }
}