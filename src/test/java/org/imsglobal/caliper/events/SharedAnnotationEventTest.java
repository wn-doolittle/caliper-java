package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.annotation.SharedAnnotation;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.profiles.Profile;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class SharedAnnotationEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubSubChapter object;
    private SharedAnnotation generated;
    private AnnotationEvent event;
    private static final Logger log = LoggerFactory.getLogger(SharedAnnotationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        //Build target reading
        object = (EpubSubChapter) TestUtils.buildEpubSubChap433();

        // Build Bookmark Annotation
        generated = TestUtils.buildSharedAnnotation(object);

        // Build event
        event = TestUtils.buildAnnotationEvent(learningContext, object, Profile.Action.SHARED, generated, 3);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Shared Annotation event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperSharedAnnotationEvent.json"), serialize(event));
    }
}