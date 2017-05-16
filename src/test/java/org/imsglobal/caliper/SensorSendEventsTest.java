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

package org.imsglobal.caliper;

import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.config.Options;
import org.imsglobal.caliper.context.JsonldContext;
import org.imsglobal.caliper.context.JsonldStringContext;
import org.imsglobal.caliper.entities.agent.CourseSection;
import org.imsglobal.caliper.entities.agent.Membership;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.Role;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.agent.Status;
import org.imsglobal.caliper.entities.resource.WebPage;
import org.imsglobal.caliper.entities.session.Session;
import org.imsglobal.caliper.events.CaliperEvent;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.requestors.Envelope;
import org.imsglobal.caliper.requestors.HttpRequestor;
import org.imsglobal.caliper.sensors.CaliperSensor;
import org.imsglobal.caliper.sensors.Client;
import org.imsglobal.caliper.sensors.HttpClient;
import org.imsglobal.caliper.sensors.Sensor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

// @Category(org.imsglobal.caliper.UnitTest.class)
public class SensorSendEventsTest {
    private JsonldContext context;
    private String id;
    private Person actor;
    private WebPage object;
    private SoftwareApplication edApp;
    private CourseSection group;
    private Membership membership;
    private WebPage referrer;
    private Session session;
    private NavigationEvent event;
    // private static final Logger log = LoggerFactory.getLogger(SensorSendEventsTest.class);

    private static final String BASE_IRI = "https://example.edu";

    @Test
    public void test() {
        context = JsonldStringContext.getDefault();

        id = "urn:id:c51570e4-f8ed-4c18-bb3a-dfe51b2cc594";

        actor = Person.builder().id(BASE_IRI.concat("/users/554433")).build();

        object = WebPage.builder()
            .id(BASE_IRI.concat("/terms/201601/courses/7/sections/1/pages/2"))
            .name("Learning Analytics Specifications")
            .description("Overview of Learning Analytics Specifications with particular emphasis on IMS Caliper.")
            .dateCreated(new DateTime(2016, 8, 1, 9, 0, 0, 0, DateTimeZone.UTC))
            .build();

        referrer = WebPage.builder().id(BASE_IRI.concat("/terms/201601/courses/7/sections/1/pages/1")).build();

        edApp = SoftwareApplication.builder().id(BASE_IRI).build();

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

        // Initialize Sensor
        CaliperSensor sensor = Sensor.create(BASE_IRI.concat("/sensors/1"));
        Options opts = Options.builder().apiKey("869e5ce5-214c-4e85-86c6-b99e8458a592").build();
        Client client = HttpClient.create(sensor.getId().concat("/clients/1"), HttpRequestor.create(opts));
        sensor.registerClient(client);

        // Create Client, register client with sensor.
        /**
        Sensor<String> manager = new Sensor<>(BASE_IRI.concat("/sensors/1"));
        Options opts = Options.builder().apiKey("869e5ce5-214c-4e85-86c6-b99e8458a592").build();
        SensorHttpClient client = new SensorHttpClient(manager.getId().concat("/clients/1"), opts);
        manager.registerClient(client.getId(), client);
         */

        // Fire event test - Send 50 envelopes containing the above event
        for (int i = 0 ; i < 50 ; i++) {
            CaliperEvent event = buildEvent(Action.NAVIGATED_TO);

            DateTime sendTime = new DateTime(2016, 11, 15, 12, 15, 0, 0, DateTimeZone.UTC);
            List<Object> data = new ArrayList<>();
            data.add(event);
            Envelope envelope = sensor.create(client.getId(), sendTime, Options.DATA_VERSION, data);
            sensor.send(envelope);
        }

        // There should be two caliperEvents queued
        assertEquals("Expect fifty Caliper events to be sent", 50,
                sensor.getStatistics().get("default").getMeasures().getCount());

        //Statistic statistics = client.getStatistics().get("default");

        // TODO - Describes test - Send five describes

        // There should be four describes queued assertEquals("Expect five describes to be sent", 5,
        // Caliper.getStatistics().getDescribes().getCount());

        // There should be two message successfully sent
        int successes = sensor.getStatistics().get("default").getSuccessful().getCount();
        assertEquals("Expect fifty messages to be sent successfully", 50, successes);

        // There should be zero failures
        int failures = sensor.getStatistics().get("default").getFailed().getCount();
        assertEquals("Expect zero message failures to be sent", 0, failures);
    }

    /**
     * Build Navigation event
     * @param action
     * @return event
     */
    private NavigationEvent buildEvent(Action action) {
        return NavigationEvent.builder()
            .context(context)
            .id(id)
            .actor(actor)
            .action(action)
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 10, 0, 0, 0, DateTimeZone.UTC))
            .referrer(referrer)
            .edApp(edApp)
            .group(group)
            .membership(membership)
            .session(session)
            .build();
    }
}