package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assessment.AssessmentItem;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.profiles.Profile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssessmentItemStartedEventTest extends EventTest {
    private LearningContext learningContext;
    private Assessment assessment;
    private AssessmentItem item;
    private Attempt attempt;
    private AssessmentItemEvent event;
    private static final Logger log = LoggerFactory.getLogger(AssessmentItemStartedEventTest.class);

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

        // Build event
        event = TestUtils.buildAssessmentItemStartedEvent(learningContext, item, Profile.Action.STARTED, attempt);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if AssessmentItem Start event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssessmentItemStartedEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void assessmentItemEventRejectsSearchedAction() {
        TestUtils.buildAssessmentItemStartedEvent(learningContext, item, Profile.Action.SEARCHED, attempt);
    }
}