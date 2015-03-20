package org.imsglobal.caliper.events;

import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.profiles.Profile;
import org.imsglobal.caliper.profiles.Profile.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class ViewEventTest extends EventTest {

    private LearningContext learningContext;
    private EpubVolume epub;
    private EpubSubChapter target;
    private ViewEvent event;
    private static final Logger log = LoggerFactory.getLogger(ViewEventTest.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Build the Learning Context
        learningContext = TestUtils.buildReadiumStudentLearningContext();

        // Build epub
        epub = TestUtils.buildEpubVolume43();

        // Build target
        target = TestUtils.buildEpubSubChap431();

        // Build event
        event = TestUtils.buildEpubViewEvent(learningContext, epub, Action.VIEWED, target);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {

        assertEquals("Test if View event is serialized to JSON with expected values",
                jsonFixture("fixtures/caliperViewEvent.json"), serialize(event));
    }

    @Test(expected=IllegalStateException.class)
    public void viewEventRejectsSearchedAction(){
        TestUtils.buildEpubViewEvent(learningContext, epub, Action.SEARCHED, target);
    }
}