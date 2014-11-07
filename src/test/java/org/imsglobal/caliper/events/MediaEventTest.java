package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.profiles.MediaProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class MediaEventTest {

    private LearningContext learningContext;
    private MediaProfile profile;
    private MediaEvent event;
    private static final Logger LOG = LoggerFactory.getLogger(NavigationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildTestLearningContext();

        // Build Video Media Profile
        profile = TestUtils.buildTestVideoMediaProfile(learningContext);

        // Add pause action and frame coordinates
        profile = TestUtils.pauseVideo(profile);

        // Build event
        event = TestUtils.buildTestMediaEvent(profile);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Media event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperMediaEvent.json"), asJson(event));
    }
}
