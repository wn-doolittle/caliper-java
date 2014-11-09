package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.profiles.AnnotationProfile;
import org.imsglobal.caliper.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class HighlightAnnotationEventTest {

    private LearningContext learningContext;
    private AnnotationProfile profile;
    private AnnotationEvent event;
    private static final Logger LOG = LoggerFactory.getLogger(NavigationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildLearningContext();

        // Build Reading Profile
        profile = TestUtils.buildAnnotationProfile(learningContext);

        // Add Highlight Annotation
        profile = TestUtils.addHighlightAnnotation(profile);

        // Build event
        event = TestUtils.buildAnnotationEvent(profile);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Highlight Annotation event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperHighlightAnnotationEvent.json"), asJson(event));
    }
}
