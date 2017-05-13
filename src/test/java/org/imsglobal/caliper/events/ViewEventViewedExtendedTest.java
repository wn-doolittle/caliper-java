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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.config.Options;
import org.imsglobal.caliper.databind.JsonFilters;
import org.imsglobal.caliper.databind.JsonObjectMapper;
import org.imsglobal.caliper.databind.JsonSimpleFilterProvider;
import org.imsglobal.caliper.entities.agent.CourseSection;
import org.imsglobal.caliper.entities.agent.Membership;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.Role;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.agent.Status;
import org.imsglobal.caliper.entities.resource.Document;
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
public class ViewEventViewedExtendedTest {
    private String id;
    private Person actor;
    private Document object;
    private SoftwareApplication edApp;
    private CourseSection group;
    private Membership membership;
    private Session session;
    private ViewEvent event;
    private ObjectNode extensionsNode;

    private static final String BASE_IRI = "https://example.edu";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        id = "3a9bd869-addc-48b1-80f6-a14b2ff591ed";

        actor = Person.builder().id(BASE_IRI.concat("/users/554433")).build();

        object = Document.builder()
            .id(BASE_IRI.concat("/etexts/200.epub"))
            .name("IMS Caliper Specification")
            .version("1.1")
            .build();

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

        extensionsNode = createExtensionsNode();

        // Build event
        event = buildEvent(Action.VIEWED);

        /*
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.SERIALIZE_ALL);
        ObjectMapper mapper = JsonObjectMapper.create(JsonInclude.Include.NON_EMPTY, provider);
        String json = mapper.writeValueAsString(event.getExtensions());
        System.out.print("EXTENSIONS_TEXT: " + json + "\n");
        */
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
        ObjectMapper mapper = JsonObjectMapper.create(Options.JACKSON_JSON_INCLUDE, provider);
        String json = mapper.writeValueAsString(event);

        String fixture = jsonFixture("fixtures/caliperEventViewViewedExtended.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void viewEventRejectsNavigatedToAction() {
        buildEvent(Action.NAVIGATED_TO);
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build View event
     * @param action
     * @return event
     */
    private ViewEvent buildEvent(Action action) {
        return ViewEvent.builder()
            .id(id)
            .actor(actor)
            .action(action)
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .edApp(edApp)
            .group(group)
            .membership(membership)
            .session(session)
            .extension(extensionsNode)
            .build();
    }

    /**
     * Create faux extensions
     * @return
     */
    private ObjectNode createExtensionsNode() {
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.SERIALIZE_ALL);
        ObjectMapper mapper = JsonObjectMapper.create(Options.JACKSON_JSON_INCLUDE, provider);

        ObjectNode jobTag = mapper.createObjectNode();
        jobTag.put("id", "example:jobTag");
        jobTag.put("type", "xsd:string");

        ObjectNode jobDate = mapper.createObjectNode();
        jobDate.put("id", "example:jobDate");
        jobDate.put("type", "xsd:dateTime");

        ObjectNode context = mapper.createObjectNode();
        context.put("id", "@id");
        context.put("type", "@type");
        context.put("example", "http://example.edu/ctx/edu");
        context.put("xsd", "http://www.w3.org/2001/XMLSchema#");
        context.put("ChronJob", "example:ChronJob");
        context.put("job", "example:job");
        context.putPOJO("jobTag", jobTag);
        context.putPOJO("jobDate", jobDate);

        ObjectNode job = mapper.createObjectNode();
        job.put("id", "https://example.edu/data/jobs/08c1233d-9ba3-40ac-952f-004c47a50ff7");
        job.put("type", "ChronJob");
        job.put("jobTag", "caliper");
        job.put("jobDate","2016-11-16T01:01:00.000Z");

        ObjectNode obj = mapper.createObjectNode();
        obj.putPOJO("@context", context);
        obj.putPOJO("job", job);

        return obj;
    }
}