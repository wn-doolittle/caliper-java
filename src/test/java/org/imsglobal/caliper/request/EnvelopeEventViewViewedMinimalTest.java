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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.Client;
import org.imsglobal.caliper.Sensor;
import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.databind.JsonFilters;
import org.imsglobal.caliper.databind.JsonObjectMapper;
import org.imsglobal.caliper.databind.JsonSimpleFilterProvider;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.reading.Document;
import org.imsglobal.caliper.events.BasicEvent;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.payload.Envelope;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
    private Person actor;
    private Document object;
    private BasicEvent event;
    private Envelope<Event> envelope;
    private DateTime sendTime;
    private List<Event> data = new ArrayList<>();
    // private static final Logger log = LoggerFactory.getLogger(HttpRequestorSingleMinimalTest.class);

    private static final String BASE_IRI = "https://example.edu";

    @Before
    public void setup() {

        // Register a Sensor client using the default constructor
        sensor = new Sensor<>(BASE_IRI.concat("/sensors/1"));
        Client client = new Client();
        client.setId(sensor.getId() + "/defaultClient");
        client.setOptions(TestUtils.getTestingOptions());
        sensor.registerClient(client.getId(), client);

        actor = Person.builder().id(BASE_IRI.concat("/users/554433")).build();

        object = Document.builder().id(BASE_IRI.concat("/etexts/201.epub")).build();

        // Build event
        event = buildEvent(Action.CREATED);

        // Add event to data array
        data.add(event);

        // Send time
        sendTime = new DateTime(2016, 11, 15, 11, 5, 1, 0, DateTimeZone.UTC);

        // Create envelope; include null properties, empty objects and empty arrays
        envelope = httpRequestor.createEnvelope(sensor, DateTime.now(), data);
    }

    @Test
    public void testSerializedEnvelope() throws Exception {
        // Serialize envelope, excluding null properties, empty objects, empty arrays and duplicate @context
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
        ObjectMapper mapper = JsonObjectMapper.create(JsonInclude.Include.NON_EMPTY, provider);
        String json = mapper.writeValueAsString(envelope);

        // Swap out sendTime=DateTime.now() in favor of fixture value (or test will most assuredly fail).
        Pattern pattern = Pattern.compile("\"sendTime\":\"[^\"]*\"");
        Matcher matcher = pattern.matcher(json);
        json = matcher.replaceFirst("\"sendTime\":\"" + sendTime +"\"");

        String fixture = jsonFixture("fixtures/caliperEnvelopeEventViewViewedMinimal.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testGeneratePayloadContentType() throws Exception {
        // Serialize envelope, excluding null properties, empty objects, empty arrays and duplicate @context
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
        ObjectMapper mapper = JsonObjectMapper.create(JsonInclude.Include.NON_EMPTY, provider);
        String json = mapper.writeValueAsString(envelope);

        // Create an HTTP StringEntity payload with the envelope JSON.
        StringEntity payload = httpRequestor.generatePayload(json, ContentType.APPLICATION_JSON);

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
    private BasicEvent buildEvent(Action action) {
        return BasicEvent.builder()
            .actor(actor)
            .action(action.getValue())
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .build();
    }
}