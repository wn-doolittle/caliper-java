/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.request;

import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.LearningContext;
import org.imsglobal.caliper.TestAgentEntities;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestEpubEntities;
import org.imsglobal.caliper.TestLisEntities;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.reading.EpubSubChapter;
import org.imsglobal.caliper.entities.reading.EpubVolume;
import org.imsglobal.caliper.entities.reading.Frame;
import org.imsglobal.caliper.entities.reading.WebPage;
import org.imsglobal.caliper.events.NavigationEvent;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class HttpRequestorTest {

    private HttpRequestor httpRequestor;
    private LearningContext learningContext;
    private EpubVolume object;
    private DigitalResource fromResource;
    private EpubSubChapter ePub;
    private Frame target;
    private NavigationEvent event;
    private String id;
    private DateTime timestamp;
    private String expectedContentType;
    private DateTime dateCreated = TestDates.getDefaultDateCreated();
    private DateTime dateModified = TestDates.getDefaultDateModified();
    private DateTime dateStarted = TestDates.getDefaultStartedAtTime();
    // private static final Logger log = LoggerFactory.getLogger(HttpRequestorTest.class);

    @Before
    public void setup() {

        httpRequestor = new HttpRequestor(TestUtils.getTestingOptions());
        id = "caliper-java_fccffd9b-68d5-4183-b563-e22136aafaa3";
        timestamp = DateTime.parse("2019-09-15T10:15:29.858-04:00");
        expectedContentType = "Content-Type: application/json";

        // Build the Learning Context
        learningContext = LearningContext.builder()
            .edApp(TestAgentEntities.buildReadiumViewerApp())
            .group(TestLisEntities.buildGroup())
            .agent(TestAgentEntities.buildStudent554433())
            .build();

        // Build object
        object = TestEpubEntities.buildEpubVolume43();

        // Build previous location
        fromResource = WebPage.builder()
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101/index.html")
            .name("American Revolution 101 Landing Page")
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .version("1.0")
            .build();

        // Build target frame
        ePub = TestEpubEntities.buildEpubSubChap431();
        target = Frame.builder()
            .id(ePub.getId())
            .name(ePub.getName())
            .isPartOf(ePub.getIsPartOf())
            .dateCreated(dateCreated)
            .dateModified(dateModified)
            .version(ePub.getVersion())
            .index(1)
            .build();

        // Build event
        event = buildEvent(Action.NAVIGATED_TO);
    }

    @Test
    public void testGeneratePayloadJson() throws Exception {
        String jsonPayload;
        jsonPayload = httpRequestor.getPayloadJson(event, id, timestamp);

        assertEquals("Test HTTP Requestor payload JSON",
            jsonFixture("fixtures/eventStorePayload.json"), jsonPayload);

    }

    @Test
    public void testGeneratePayloadContentType() throws Exception {
        StringEntity payload;
        payload = httpRequestor.generatePayload(event, id, timestamp);

        assertEquals(expectedContentType, payload.getContentType().toString());
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build Navigation event
     * @param action
     * @return event
     */
    private NavigationEvent buildEvent(Action action) {
        return NavigationEvent.builder()
            .edApp(learningContext.getEdApp())
            .group(learningContext.getGroup())
            .actor((Person) learningContext.getAgent())
            .action(action)
            .object(object)
            .target(target)
            .fromResource(fromResource)
            .startedAtTime(dateStarted)
            .build();
    }
}