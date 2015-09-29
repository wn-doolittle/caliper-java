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

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.Client;
import org.imsglobal.caliper.Sensor;
import org.imsglobal.caliper.TestDates;
import org.imsglobal.caliper.TestFreeFormAgent;
import org.imsglobal.caliper.TestFreeFormEntity;
import org.imsglobal.caliper.TestFreeFormEvent;
import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.payload.Envelope;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;
import static org.junit.Assert.assertEquals;

@Category(org.imsglobal.caliper.UnitTest.class)
public class HttpRequestorSingleMinimalEventTest {

    private Sensor<String> sensor ;
    private HttpRequestor<Event> httpRequestor = new HttpRequestor<>(TestUtils.getTestingOptions());
    private Agent actor;
    private TestFreeFormEntity object;
    private TestFreeFormEvent event;
    private DateTime eventTime = TestDates.getDefaultStartedAtTime();
    private List<Event> data = new ArrayList<>();
    // private static final Logger log = LoggerFactory.getLogger(HttpRequestorTest.class);

    @Before
    public void setup() {

        // Register a Sensor client using the default constructor
        sensor = new Sensor<>("https://example.edu/sensor/001");
        Client client = new Client();
        client.setId(sensor.getId() + "/defaultClient");
        client.setOptions(TestUtils.getTestingOptions());
        sensor.registerClient(client.getId(), client);

        // Build actor
        String actorId = "https://example.edu/user/554433";
        String actorType = "http://purl.imsglobal.org/caliper/v1/lis/Person";
        actor = TestFreeFormAgent.create(null, actorId, actorType);

        // Build object
        String objectId = "https://example.com/viewer/book/34843#epubcfi(/4/3)";
        String objectType = "http://www.idpf.org/epub/vocab/structure/#volume";
        object = TestFreeFormEntity.create(null, objectId, objectType);

        // Build event
        event = buildEvent(Action.VIEWED);

        // Add event to data array
        data.add(event);
    }

    @Test
    public void testSerializedEnvelope() throws Exception {

        // Create envelope; include null properties, empty objects and empty arrays
        Envelope<Event> envelope = httpRequestor.createEnvelope(sensor, DateTime.now(), data);

        // Serialize envelope, excluding null properties, empty objects and empty arrays
        String json = httpRequestor.serializeEnvelope(envelope, JsonInclude.Include.NON_EMPTY);

        // Swap out sendTime=DateTime.now() in favor of fixture value (or test will most assuredly fail).
        Pattern pattern = Pattern.compile("\"sendTime\":\"[^\"]*\"");
        Matcher matcher = pattern.matcher(json);
        json = matcher.replaceFirst("\"sendTime\":\"" + TestDates.getDefaultSendTime() +"\"");

        String fixture = jsonFixture("fixtures/caliperEnvelopeEventViewViewedMinimal.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testGeneratePayloadContentType() throws Exception {

        // Create envelope
        Envelope<Event> envelope = httpRequestor.createEnvelope(sensor, DateTime.now(), data);

        // Serialize envelope; include null properties, empty objects and empty arrays
        String json = httpRequestor.serializeEnvelope(envelope, JsonInclude.Include.NON_EMPTY);

        // Create an HTTP StringEntity payload with the envelope JSON.
        StringEntity payload = httpRequestor.generatePayload(json, ContentType.APPLICATION_JSON);

        //System.out.println("CONTENT-TYPE:" + payload.getContentType().toString());

        assertEquals("Content-Type: application/json; charset=UTF-8", payload.getContentType().toString());
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build minimal event
     * @param action
     * @return event
     */
    private TestFreeFormEvent buildEvent(Action action) {
        return TestFreeFormEvent.builder()
            .actor(actor)
            .action(action.getValue())
            .object(object)
            .eventTime(eventTime)
            .build();
    }
}