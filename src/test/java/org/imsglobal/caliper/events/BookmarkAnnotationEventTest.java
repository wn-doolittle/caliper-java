package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.annotation.BookmarkAnnotation;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.actions.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class BookmarkAnnotationEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubSubChapter object;
    private String key;
    private BookmarkAnnotation generated;
    private AnnotationEvent event;
    private static final Logger log = LoggerFactory.getLogger(BookmarkAnnotationEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        //Build Reading
        object = (EpubSubChapter) TestUtils.buildEpubSubChap432();

        // Build Bookmark Annotation
        generated = TestUtils.buildBookmarkAnnotation(object);

        // Build event
        event = TestUtils.buildAnnotationEvent(learningContext, object, Action.BOOKMARKED, generated, 2);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Bookmark Annotation event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperBookmarkAnnotationEvent.json"), serialize(event));
    }
}