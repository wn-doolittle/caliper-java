package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.annotation.TagAnnotation;
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
public class TagAnnotationEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubSubChapter object;
    private String key;
    private TagAnnotation generated;
    private AnnotationEvent event;
    private static final Logger log = LoggerFactory.getLogger(TagAnnotationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        //Build target reading
        object = TestUtils.buildEpubSubChap434();

        // Build Bookmark Annotation
        generated = TestUtils.buildTagAnnotation(object);

        // Build event
        event = TestUtils.buildAnnotationEvent(learningContext, object, Profile.Action.TAGGED, generated, 4);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Tag Annotation event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperTagAnnotationEvent.json"), serialize(event));
    }
}