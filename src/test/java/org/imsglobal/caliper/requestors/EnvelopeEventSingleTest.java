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

package org.imsglobal.caliper.requestors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.Client;
import org.imsglobal.caliper.Config;
import org.imsglobal.caliper.Sensor;
import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.databind.JsonFilters;
import org.imsglobal.caliper.databind.JsonObjectMapper;
import org.imsglobal.caliper.databind.JsonSimpleFilterProvider;
import org.imsglobal.caliper.entities.agent.CourseSection;
import org.imsglobal.caliper.entities.agent.Membership;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.Role;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.agent.Status;
import org.imsglobal.caliper.entities.resource.Assessment;
import org.imsglobal.caliper.entities.resource.Attempt;
import org.imsglobal.caliper.entities.session.Session;
import org.imsglobal.caliper.events.AssessmentEvent;
import org.imsglobal.caliper.events.Event;
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
public class EnvelopeEventSingleTest {

    private Sensor<String> sensor ;
    private HttpRequestor<Event> httpRequestor = new HttpRequestor<>(TestUtils.getTestingOptions());
    private Envelope<Event> envelope;
    private List<Event> data = new ArrayList<>();
    private String uuid;
    private Person actor;
    private Assessment object;
    private Attempt generated;
    private SoftwareApplication edApp;
    private CourseSection group;
    private Membership membership;
    private Session session;
    private AssessmentEvent event;
    private DateTime sendTime;
    // private static final Logger log = LoggerFactory.getLogger(HttpRequestorSingleEventTest.class);

    private static final String BASE_IRI = "https://example.edu";

    @Before
    public void setup() {

        // Register a Sensor client using the default constructor
        sensor = new Sensor<>(BASE_IRI.concat("/sensors/1"));
        Client client = new Client();
        client.setId(sensor.getId() + "/defaultClient");
        client.setOptions(TestUtils.getTestingOptions());
        sensor.registerClient(client.getId(), client);

        uuid = "c51570e4-f8ed-4c18-bb3a-dfe51b2cc594";

        actor = Person.builder().id(BASE_IRI.concat("/users/554433")).build();

        object = Assessment.builder()
            .id(BASE_IRI.concat("/terms/201601/courses/7/sections/1/assess/1"))
            .name("Quiz One")
            .dateToStartOn(new DateTime(2016, 11, 14, 5, 0, 0, 0, DateTimeZone.UTC))
            .dateToSubmit(new DateTime(2016, 11, 18, 11, 59, 59, 0, DateTimeZone.UTC))
            .maxAttempts(2)
            .maxSubmits(2)
            .maxScore(25)
            .version("1.0")
            .build();

        generated = Attempt.builder()
            .id(BASE_IRI.concat("/terms/201601/courses/7/sections/1/assess/1/users/554433/attempts/1"))
            .assignable(Assessment.builder().id(object.getId()).build())
            .assignee(actor)
            .count(1)
            .dateCreated(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .startedAtTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .build();

        edApp = SoftwareApplication.builder().id(BASE_IRI).version("v2").build();

        group = CourseSection.builder().id(BASE_IRI.concat("/terms/201601/courses/7/sections/1"))
            .courseNumber("CPS 435-01")
            .academicSession("Fall 2016")
            .build();

        membership = Membership.builder()
            .id(BASE_IRI.concat("/terms/201601/courses/7/sections/1/rosters/1"))
            .member(actor)
            .organization(CourseSection.builder().id(group.getId()).build())
            .status(Status.ACTIVE)
            .role(Role.LEARNER)
            .dateCreated(new DateTime(2016, 8, 1, 6, 0, 0, 0, DateTimeZone.UTC))
            .build();

        session = Session.builder()
            .id(BASE_IRI.concat("/sessions/1f6442a482de72ea6ad134943812bff564a76259"))
            .startedAtTime(new DateTime(2016, 11, 15, 10, 0, 0, 0, DateTimeZone.UTC))
            .build();

        // Build event
        event = buildEvent(Action.STARTED);

        // Add event to data array
        data.add(event);

        // Send time
        sendTime = new DateTime(2016, 11, 15, 11, 5, 1, 0, DateTimeZone.UTC);

        // Create envelope
        envelope = httpRequestor.createEnvelope(sensor, DateTime.now(), Config.REMOTE_CALIPER_JSONLD_CONTEXT.value(), data);
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

        String fixture = jsonFixture("fixtures/caliperEnvelopeEventSingle.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testGeneratePayloadContentType() throws Exception {
        // Serialize envelope; include null properties, empty objects and empty arrays
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
        ObjectMapper mapper = JsonObjectMapper.create(JsonInclude.Include.NON_EMPTY, provider);
        String json = mapper.writeValueAsString(envelope);

        // Create an HTTP StringEntity envelopes with the envelope JSON.
        StringEntity payload = httpRequestor.generatePayload(json, ContentType.APPLICATION_JSON);

        assertEquals("Content-Type: application/json; charset=UTF-8", payload.getContentType().toString());
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
    private AssessmentEvent buildEvent(Action action) {
        return AssessmentEvent.builder()
            .uuid(uuid)
            .actor(actor)
            .action(action)
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .generated(generated)
            .edApp(edApp)
            .group(group)
            .membership(membership)
            .session(session)
            .build();
    }
}