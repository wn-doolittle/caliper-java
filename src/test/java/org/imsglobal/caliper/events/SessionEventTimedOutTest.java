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

package org.imsglobal.caliper.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.config.Options;
import org.imsglobal.caliper.databind.JsonFilters;
import org.imsglobal.caliper.databind.JsonObjectMapper;
import org.imsglobal.caliper.databind.JsonSimpleFilterProvider;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.session.Session;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;

@Category(org.imsglobal.caliper.UnitTest.class)
public class SessionEventTimedOutTest {
    private String uuid;
    private SoftwareApplication actor;
    private Session object;
    private SessionEvent event;

    private static final String BASE_IRI = "https://example.edu";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        uuid = "4e61cf6c-ffbe-45bc-893f-afe7ad4079dc";

        actor = SoftwareApplication.builder().id(BASE_IRI).build();

        object = Session.builder()
            .id(BASE_IRI.concat("/sessions/7d6b88adf746f0692e2e873308b78c60fb13a864"))
            .user(Person.builder().id(BASE_IRI.concat("/users/112233")).build())
            .dateCreated(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .startedAtTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .endedAtTime(new DateTime(2016, 11, 15, 11, 15, 0, 0, DateTimeZone.UTC))
            .duration("PT3600S")
            .build();

        // Build event
        event = buildEvent(Action.TIMED_OUT);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
        ObjectMapper mapper = JsonObjectMapper.create(Options.JACKSON_JSON_INCLUDE, provider);
        String json = mapper.writeValueAsString(event);

        String fixture = jsonFixture("fixtures/caliperEventSessionTimedOut.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void sessionEventRejectsLikedAction() {
        buildEvent(Action.LIKED);
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build Session Timeout event.  Note that the actor is the edApp and
     * the membership of the actor is not specified.
     * @param action
     * @return event
     */
    private SessionEvent buildEvent(Action action) {
        return SessionEvent.builder()
            .uuid(uuid)
            .actor(actor)
            .action(action)
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 11, 15, 0, 0, DateTimeZone.UTC))
            .edApp(actor)
            .build();
    }
}