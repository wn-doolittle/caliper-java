package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assessment.AssessmentItem;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.assignable.Response;
import org.imsglobal.caliper.profiles.AssessmentItemProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssessmentItemCompletedEventTest extends EventTest {
    private LearningContext learningContext;
    private Assessment assessment;
    private AssessmentItem item;
    private Attempt attempt;
    private Response response;
    private String key;
    private AssessmentItemEvent event;
    private static final Logger LOG = LoggerFactory.getLogger(AssessmentItemCompletedEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildAssessmentStudentLearningContext();

        // Build assessment
        assessment = TestUtils.buildAssessment();

        // Generate attempt
        attempt = TestUtils.buildAssessmentItemAttempt(learningContext, assessment);

        // Build assessment and get assessment item 1
        item = TestUtils.buildAssessment().getAssessmentItems().get(0);

        // Action
        key = AssessmentItemProfile.Actions.STARTED.key();

        // Response
        response = TestUtils.buildAssessmentItemResponse(attempt, "2 July 1776");

        // Build event
        event = TestUtils.buildAssessmentItemCompletedEvent(learningContext, item, key, response);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Assessment Item Completed event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssessmentItemCompletedEvent.json"), serialize(event));
    }
}