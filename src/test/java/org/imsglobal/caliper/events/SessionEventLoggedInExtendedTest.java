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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.context.JsonldContext;
import org.imsglobal.caliper.context.JsonldStringContext;
import org.imsglobal.caliper.databind.JxnCoercibleSimpleModule;
import org.imsglobal.caliper.databind.JxnFilters;
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
public class SessionEventLoggedInExtendedTest {
    private JsonldContext context;
    private String id;
    private Person actor;
    private SoftwareApplication object, edApp;
    private Session session;
    private Object extensions;
    private SessionEvent event;

    private static final String BASE_IRI = "https://example.edu";
    private static final String SECTION_IRI = BASE_IRI.concat("/terms/201601/courses/7/sections/1");

    @Before
    public void setUp() throws Exception {
        context = JsonldStringContext.getDefault();

        id = "urn:uuid:4ec2c31e-3ec0-4fe1-a017-b81561b075d7";

        actor = Person.builder().id(BASE_IRI.concat("/users/554433")).build();

        object = SoftwareApplication.builder().id(BASE_IRI).version("v2").build();

        edApp = SoftwareApplication.builder().id(object.getId()).coercedToId(true).build();

        extensions = createExtensionsNode();

        session = Session.builder()
            .id(BASE_IRI.concat("/sessions/1f6442a482de72ea6ad134943812bff564a76259"))
            .user(Person.builder().id(actor.getId()).coercedToId(true).build())
            .dateCreated(new DateTime(2016, 11, 15, 20, 11, 15, 0, DateTimeZone.UTC))
            .startedAtTime(new DateTime(2016, 11, 15, 20, 11, 15, 0, DateTimeZone.UTC))
            .extensions(extensions)
            .build();

        // Build event
        event = buildEvent(Action.LOGGED_IN);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        SimpleFilterProvider provider = new SimpleFilterProvider()
            .setFailOnUnknownId(true);

        ObjectMapper mapper = new ObjectMapper()
            .setDateFormat(new ISO8601DateFormat())
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .setFilterProvider(provider)
            .registerModules(new JodaModule(), new JxnCoercibleSimpleModule());

        String json = mapper.writeValueAsString(event);

        String fixture = jsonFixture("fixtures/caliperEventSessionLoggedInExtended.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void sessionEventRejectsSearchedAction() {
        buildEvent(Action.SEARCHED);
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build Session login event.
     * @param action
     * @return event
     */
    private SessionEvent buildEvent(Action action) {
        return SessionEvent.builder()
            .context(context)
            .id(id)
            .actor(actor)
            .action(action)
            .object(object)
            .edApp(edApp)
            .eventTime(new DateTime(2016, 11, 15, 20, 11, 15, 0, DateTimeZone.UTC))
            .session(session)
            .build();
    }

    /**
     * Create faux extensions
     * @return
     */
    private ObjectNode createExtensionsNode() {
        SimpleFilterProvider provider = new SimpleFilterProvider()
            .setFailOnUnknownId(true)
            .addFilter(JxnFilters.SERIALIZE_ALL.id(), JxnFilters.SERIALIZE_ALL.filter());

        ObjectMapper mapper = new ObjectMapper()
            .setDateFormat(new ISO8601DateFormat())
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .setFilterProvider(provider)
            .registerModule(new JodaModule());

        ObjectNode request = mapper.createObjectNode();
        request.put("id", "d71016dc-ed2f-46f9-ac2c-b93f15f38fdc");
        request.put("hostname", "example.com");
        request.put("userAgent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

        ObjectNode extensions = mapper.createObjectNode();
        extensions.putPOJO("request", request);

        return extensions;
    }
}


/**
{/**
    "@context": "http://purl.imsglobal.org/ctx/caliper/v1p1",
    "id": "urn:uuid:4ec2c31e-3ec0-4fe1-a017-b81561b075d7",
    "type": "SessionEvent",
    "actor": {
    "id": "https://example.edu/users/554433",
    "type": "Person"
    },
    "action": "LoggedIn",
    "object": {
    "id": "https://example.edu",
    "type": "SoftwareApplication",
    "version": "v2"
    },
    "eventTime": "2016-11-15T20:11:15.000Z",
    "edApp": "https://example.edu",
    "session": {
    "id": "https://example.edu/sessions/1f6442a482de72ea6ad134943812bff564a76259",
    "type": "Session",
    "user": "https://example.edu/users/554433",
    "dateCreated": "2016-11-15T20:11:15.000Z",
    "startedAtTime": "2016-11-15T20:11:15.000Z",
    "extensions": {
    "request":  {
    "id": "d71016dc-ed2f-46f9-ac2c-b93f15f38fdc",
    "hostname": "example.com",
    "userAgent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36"
    }
    }
    }
    }

    */