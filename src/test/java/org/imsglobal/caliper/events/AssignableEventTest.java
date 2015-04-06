package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.profiles.Profile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class AssignableEventTest extends EventTest {
    private LearningContext learningContext;
    private Assessment assessment;
    private AssignableEvent event;
    private static final Logger log = LoggerFactory.getLogger(AssignableEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildAssessmentStudentLearningContext();

        // Build assessment
        assessment = TestUtils.buildAssessment();

        // Build event
        event = TestUtils.buildAssessmentAssignableEvent(learningContext, assessment, Profile.Action.ACTIVATED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Assignable event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperAssignableEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void assignableEventRejectsSearchedAction() {
        TestUtils.buildAssessmentAssignableEvent(learningContext, assessment, Profile.Action.SEARCHED);
    }
}