package org.imsglobal.caliper.events;

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
public class NavigationEventTest {

    private static final Logger LOG = LoggerFactory.getLogger(NavigationEventTest.class);

    private Event caliperEvent;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        caliperEvent = TestUtils.getTestNavigationEvent();
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        assertEquals("Test if Event can be serialized to JSON",
        jsonFixture("fixtures/caliperEvent.json"), asJson(caliperEvent));
    }
}