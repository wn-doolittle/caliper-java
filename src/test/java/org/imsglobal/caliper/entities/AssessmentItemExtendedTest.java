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

package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.imsglobal.caliper.context.JsonldStringContext;
import org.imsglobal.caliper.databind.JxnCoercibleSimpleModule;
import org.imsglobal.caliper.databind.JxnFilters;
import org.imsglobal.caliper.entities.resource.Assessment;
import org.imsglobal.caliper.entities.resource.AssessmentItem;
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
public class AssessmentItemExtendedTest {
    private AssessmentItem entity;

    private static final String BASE_IRI = "https://example.edu";
    private static final String SECTION_IRI = BASE_IRI.concat("/terms/201601/courses/7/sections/1");

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        // Parent
        Assessment assessment = Assessment.builder()
            .id("https://example.edu/terms/201601/courses/7/sections/1/assess/1")
            .build();

        // Extensions
        ObjectNode extensions = createExtensionsNode();

        entity = AssessmentItem.builder()
            .context(JsonldStringContext.getDefault())
            .id(SECTION_IRI.concat("/assess/1/items/3"))
            .isPartOf(assessment)
            .dateCreated(new DateTime(2016, 8, 1, 6, 0, 0, 0, DateTimeZone.UTC))
            .datePublished(new DateTime(2016, 8, 15, 9, 30, 0, 0, DateTimeZone.UTC))
            .maxScore(1.0)
            .maxSubmits(2)
            .isTimeDependent(false)
            .extensions(extensions)
            .build();
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

        String json = mapper.writeValueAsString(entity);

        String fixture = jsonFixture("fixtures/caliperEntityAssessmentItemExtended.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @After
    public void teardown() {
        entity = null;
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

        ObjectNode extensions = mapper.createObjectNode();
        extensions.put("questionType", "Dichotomous");
        extensions.put("questionText", "Is a Caliper SoftwareApplication a subtype of Caliper Agent?");
        extensions.put("correctResponse","yes");

        return extensions;
    }
}