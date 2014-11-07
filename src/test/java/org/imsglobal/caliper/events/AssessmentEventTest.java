package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.assessment.Assessment;
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
public class AssessmentEventTest {

    private LearningContext learningContext;
    private Assessment assessment;
    private AssessmentProfile profile;
    private AssessmentEvent event;
    private static final Logger LOG = LoggerFactory.getLogger(NavigationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildTestLearningContext();

        // Build assessment
        assessment = TestUtils.buildTestAssessment(learningContext);

        // Build Profile
        profile = TestUtils.buildTestAssessmentProfile(learningContext, assessment);

        // Start Assignable
        profile = TestUtils.startTestAssessment(profile);

        // Build event
        event = TestUtils.buildTestAssessmentEvent(profile);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Assessment event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssessmentEvent.json"), asJson(event));
    }
}
