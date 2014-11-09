package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assessment.AssessmentItem;
import org.imsglobal.caliper.profiles.AssessmentProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssessmentItemEventTest {
    private LearningContext learningContext;
    private Assessment assessment;
    private AssessmentProfile profile;
    private AssessmentItemEvent event;
    private static final Logger LOG = LoggerFactory.getLogger(NavigationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildLearningContext();

        // Build assessment
        assessment = TestUtils.buildAssessment(learningContext);

        // Build Profile
        profile = TestUtils.buildAssessmentProfile(learningContext, assessment);

        // Start AssessmentItem, record item attempted
        AssessmentItem itemAttempted = profile.getAssessment().getAssessmentItems().get(0);
        profile = TestUtils.startAssessmentItem(profile, itemAttempted);

        // Build event
        event = TestUtils.buildAssessmentItemEvent(profile, 0);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Assessment Item event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssessmentItemEvent.json"), asJson(event));
    }
}
