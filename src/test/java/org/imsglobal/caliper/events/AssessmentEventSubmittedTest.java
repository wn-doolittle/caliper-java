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
import org.imsglobal.caliper.entities.agent.CourseSection;
import org.imsglobal.caliper.entities.agent.Membership;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.Role;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;
import org.imsglobal.caliper.entities.agent.Status;
import org.imsglobal.caliper.entities.resource.Assessment;
import org.imsglobal.caliper.entities.resource.Attempt;
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
public class AssessmentEventSubmittedTest {
    private String uuid;
    private Person actor;
    private Assessment assignable;
    private Attempt object;
    private SoftwareApplication edApp;
    private CourseSection group;
    private Membership membership;
    private Session session;
    private AssessmentEvent event;

    private static final String BASE_IRI = "https://example.edu";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        uuid = "dad88464-0c20-4a19-a1ba-ddf2f9c3ff33";

        actor = Person.builder().id(BASE_IRI.concat("/users/554433")).build();

        assignable = Assessment.builder()
            .id(BASE_IRI.concat("/terms/201601/courses/7/sections/1/assess/1"))
            .name("Quiz One")
            .dateToStartOn(new DateTime(2016, 11, 14, 5, 0, 0, 0, DateTimeZone.UTC))
            .dateToSubmit(new DateTime(2016, 11, 18, 11, 59, 59, 0, DateTimeZone.UTC))
            .maxAttempts(2)
            .maxSubmits(2)
            .maxScore(25)
            .version("1.0")
            .build();

        object = Attempt.builder()
            .id(BASE_IRI.concat("/terms/201601/courses/7/sections/1/assess/1/users/554433/attempts/1"))
            .assignable(assignable)
            .assignee(actor)
            .count(1)
            .dateCreated(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .startedAtTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .endedAtTime(new DateTime(2016, 11, 15, 10, 25, 30, 0, DateTimeZone.UTC))
            .duration("PT10M30S")
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

        event = buildEvent(Action.SUBMITTED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        SimpleFilterProvider provider = JsonSimpleFilterProvider.create(JsonFilters.EXCLUDE_CONTEXT);
        ObjectMapper mapper = JsonObjectMapper.create(Options.JACKSON_JSON_INCLUDE, provider);
        String json = mapper.writeValueAsString(event);

        String fixture = jsonFixture("fixtures/caliperEventAssessmentSubmitted.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expected=IllegalArgumentException.class)
    public void assessmentEventRejectsSearchedAction() {
        buildEvent(Action.SUBSCRIBED);
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build Assessment event
     * @param action
     * @return event
     */
    private AssessmentEvent buildEvent(Action action) {
        return AssessmentEvent.builder()
            .uuid(uuid)
            .actor(actor)
            .action(action)
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 10, 25, 30, 0, DateTimeZone.UTC))
            .edApp(edApp)
            .group(group)
            .membership(membership)
            .session(session)
            .build();
    }
}