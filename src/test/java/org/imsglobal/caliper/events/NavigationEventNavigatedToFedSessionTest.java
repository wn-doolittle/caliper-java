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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
import org.imsglobal.caliper.entities.resource.Document;
import org.imsglobal.caliper.entities.resource.WebPage;
import org.imsglobal.caliper.entities.session.LtiSession;
import org.imsglobal.caliper.entities.session.Session;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;

@Category(org.imsglobal.caliper.UnitTest.class)
public class NavigationEventNavigatedToFedSessionTest {
    private String uuid;
    private Person actor;
    private Document object;
    private SoftwareApplication edApp;
    private CourseSection group;
    private Membership membership;
    private WebPage referrer;
    private LtiSession federatedSession;
    private Session session;
    private NavigationEvent event;
    private ObjectNode launchParams;

    private static final String BASE_IRI_COM = "https://example.com";
    private static final String BASE_IRI_EDU = "https://example.edu";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        uuid = "4be6d29d-5728-44cd-8a8f-3d3f07e46b61";

        actor = Person.builder().id(BASE_IRI_EDU.concat("/users/554433")).build();

        object = Document.builder()
            .id(BASE_IRI_COM.concat("/lti/reader/202.epub"))
            .mediaType("application/epub+zip")
            .name("Caliper Case Studies")
            .dateCreated(new DateTime(2016, 8, 1, 9, 0, 0, 0, DateTimeZone.UTC))
            .build();

        referrer = WebPage.builder().id(BASE_IRI_EDU.concat("/terms/201601/courses/7/sections/1/pages/4")).build();

        edApp = SoftwareApplication.builder().id(BASE_IRI_COM).build();

        group = CourseSection.builder().id(BASE_IRI_EDU.concat("/terms/201601/courses/7/sections/1"))
            .courseNumber("CPS 435-01")
            .academicSession("Fall 2016")
            .build();

        membership = Membership.builder()
            .id(BASE_IRI_EDU.concat("/terms/201601/courses/7/sections/1/rosters/1"))
            .member(actor)
            .organization(CourseSection.builder().id(group.getId()).build())
            .status(Status.ACTIVE)
            .role(Role.LEARNER)
            .dateCreated(new DateTime(2016, 8, 1, 6, 0, 0, 0, DateTimeZone.UTC))
            .build();

        session = Session.builder()
            .id(BASE_IRI_COM.concat("/sessions/b533eb02823f31024e6b7f53436c42fb99b31241"))
            .startedAtTime(new DateTime(2016, 11, 15, 10, 0, 0, 0, DateTimeZone.UTC))
            .build();

        launchParams = createExtensionsNode();

        federatedSession = LtiSession.builder()
            .id(BASE_IRI_COM.concat("/sessions/b533eb02823f31024e6b7f53436c42fb99b31241"))
            .user(actor)
            .launchParameters(launchParams)
            .dateCreated(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .startedAtTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .build();

        // Build event
        event = buildEvent(Action.NAVIGATED_TO);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
        ObjectMapper mapper = JsonObjectMapper.create(JsonInclude.Include.NON_EMPTY, provider);
        String json = mapper.writeValueAsString(event);

        String fixture = jsonFixture("fixtures/caliperEventNavigationNavigatedToFedSession.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void navigationEventRejectsViewedAction() {
        buildEvent(Action.VIEWED);
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build Navigation event
     *
     * @param action
     * @return event
     */
    private NavigationEvent buildEvent(Action action) {
        return NavigationEvent.builder()
            .uuid(uuid)
            .actor(actor)
            .action(action)
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .referrer(referrer)
            .edApp(edApp)
            .group(group)
            .membership(membership)
            .session(session)
            .federatedSession(federatedSession)
            .build();
    }

    /**
     * Parse JSON string into a JsonNode tree model.
     * @param json
     * @return JsonNode
     */
    private JsonNode createJsonNode(String json) {
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.SERIALIZE_ALL);
        ObjectMapper mapper = JsonObjectMapper.create(JsonInclude.Include.NON_EMPTY, provider);

        try {
            return mapper.readValue(json, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create faux extensions
     * @return
     */
    private ObjectNode createExtensionsNode() {
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.SERIALIZE_ALL);
        ObjectMapper mapper = JsonObjectMapper.create(JsonInclude.Include.NON_EMPTY, provider);

        ObjectNode jobTitle = mapper.createObjectNode();
        jobTitle.put("id", "sdo:jobTitle");
        jobTitle.put("type", "xsd:string");

        ObjectNode givenName = mapper.createObjectNode();
        givenName.put("id", "sdo:givenName");
        givenName.put("type", "xsd:string");

        ObjectNode familyName = mapper.createObjectNode();
        familyName.put("id", "sdo:familyName");
        familyName.put("type", "xsd:string");

        ObjectNode email = mapper.createObjectNode();
        email.put("id", "sdo:email");
        email.put("type", "xsd:string");

        ObjectNode url = mapper.createObjectNode();
        url.put("id", "sdo:url");
        url.put("type", "xsd:string");

        ObjectNode context = mapper.createObjectNode();
        context.put("sdo", "http://schema.org/");
        context.put("xsd", "http://www.w3.org/2001/XMLSchema#");
        context.putPOJO("jobTitle", jobTitle);
        context.putPOJO("givenName", givenName);
        context.putPOJO("familyName", familyName);
        context.putPOJO("email", email);
        context.putPOJO("url", url);

        ObjectNode instructor = mapper.createObjectNode();
        instructor.putPOJO("@context", context);
        instructor.put("id", "https://example.edu/faculty/trighaversine");
        instructor.put("type", "Person");
        instructor.put("jobTitle", "Professor");
        instructor.put("givenName", "Trig");
        instructor.put("familyName", "Haversine");
        instructor.put("email", "trighaversine@example.edu");
        instructor.put("url", "https://example.edu/faculty/trighaversine");

        ObjectNode params = mapper.createObjectNode();
        params.put("lti_message_type", "basic-lti-launch-request");
        params.put("lti_version", "LTI-2p0");
        params.put("resource_link_id", "88391-e1919-bb3456");
        params.put("context_id", "8213060-006f-27b2066ac545");
        params.put("launch_presentation_document_target", "iframe");
        params.put("launch_presentation_height", 240);
        params.put("launch_presentation_return_url", "https://example.edu/terms/201601/courses/7/sections/1/pages/5");
        params.put("launch_presentation_width", 320);
        params.put("roles", "Learner,Student");
        params.put("tool_consumer_instance_guid", "example.edu");
        params.put("user_id", "0ae836b9-7fc9-4060-006f-27b2066ac545");
        params.put("context_type", "CourseSection");
        params.put("launch_presentation_locale", "en-US");
        params.put("launch_presentation_css_url", "https://example.edu/css/tool.css");
        params.put("role_scope_mentor", "f5b2cc6c-8c5c-24e8-75cc-fac5,dc19e42c-b0fe-68b8-167e-4b1a");
        params.put("custom_caliper_session_id", "https://example.edu/sessions/1f6442a482de72ea6ad134943812bff564a76259");
        params.put("custom_context_title", "CPS 435 Learning Analytics");
        params.put("custom_context_label", "CPS 435");
        params.put("custom_resource_link_title", "LTI tool");
        params.put("custom_user_image", "https://example.edu/users/554433/profile/avatar.jpg");
        params.putPOJO("ext_vnd_instructor", instructor);

        return params;
    }
}