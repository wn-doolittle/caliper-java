package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.annotation.HighlightAnnotation;
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
public class HighlightAnnotationEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubSubChapter object;
    private HighlightAnnotation generated;
    private AnnotationEvent event;
    private static final Logger log = LoggerFactory.getLogger(HighlightAnnotationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        //Build target reading
        object = (EpubSubChapter) TestUtils.buildEpubSubChap431();

        // Build Bookmark Annotation
        generated = TestUtils.buildHighlightAnnotation(object);

        // Build event
        event = TestUtils.buildAnnotationEvent(learningContext, object, Profile.Action.HIGHLIGHTED, generated, 1);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Highlight Annotation event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperHighlightAnnotationEvent.json"), serialize(event));
    }

    @Test(expected=IllegalArgumentException.class)
    public void annotationEventRejectsSearchedAction() {
        TestUtils.buildAnnotationEvent(learningContext, object, Profile.Action.SEARCHED, generated, 1);
    }
}