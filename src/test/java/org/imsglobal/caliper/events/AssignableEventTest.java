package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.profiles.AssignableProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssignableEventTest {
    private LearningContext learningContext;
    private Assessment assessment;
    private AssignableProfile profile;
    private AssignableEvent event;
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
        profile = TestUtils.buildAssessmentAssignableProfile(learningContext, assessment);

        // Start Assignable
        profile = TestUtils.startAssignableAssessment(profile);

        // Build event
        event = TestUtils.buildAssignableEvent(profile);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Assignable event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssignableEvent.json"), asJson(event));
    }
}
