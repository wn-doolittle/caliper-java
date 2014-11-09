package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.profiles.AnnotationProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class BookmarkAnnotationEventTest {

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

        // Add Bookmark Annotation
        profile = TestUtils.addBookmarkAnnotation(profile);

        // Build event
        event = TestUtils.buildAnnotationEvent(profile);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Bookmark Annotation event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperBookmarkAnnotationEvent.json"), asJson(event));
    }
}