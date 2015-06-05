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

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assessment.AssessmentItem;

/**
 * Assessment entities used to construct Event tests.
 */
public class TestAssessmentEntities {

    /**
     * Constructor
     */
    public TestAssessmentEntities() {

    }

    /**
     * Sample Assessment.  Pass a slim version of the parent assessment to .assessmentItems(Assessment parent)
     * in order to avoid generating an infinite loop during instance construction.
     * @return assessment
     */
    public static final Assessment buildAssessment() {
        return Assessment.builder()
            .id("https://example.edu/politicalScience/2015/american-revolution-101/assessment/001")
            .name("American Revolution - Key Figures Assessment")
            .dateCreated(TestDates.getDefaultDateModified())
            .datePublished(TestDates.getDefaultDatePublished())
            .version("1.0")
            .dateToActivate(TestDates.getDefaultDateToActivate())
            .dateToShow(TestDates.getDefaultDateToShow())
            .dateToStartOn(TestDates.getDefaultDateToStartOn())
            .dateToSubmit(TestDates.getDefaultDateToSubmit())
            .maxAttempts(2)
            .maxSubmits(2)
            .maxScore(3)
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Sample assessment item.
     * @param assessment
     * @return assessment item
     */
    public static final AssessmentItem buildAssessmentItem01(Assessment assessment) {
        return AssessmentItem.builder()
            .id("https://example.edu/politicalScience/2015/american-revolution-101/assessment/001/item/001")
            .name("Assessment Item 1")
            .isPartOf(assessment)
            .version("1.0")
            .maxAttempts(2)
            .maxSubmits(2)
            .maxScore(1)
            .isTimeDependent(false)
            .build();
    }

    /**
     * Sample assessment items. Build a slim version of the parent assessment in order to avoid
     * generating an infinite loop by setting the parent instance's assessmentItems property.
     * @return immutable list
     */
    public static final ImmutableList<AssessmentItem> buildAssessmentItems(Assessment parent) {
        return ImmutableList.<AssessmentItem>builder()
            .add(AssessmentItem.builder()
                .id("https://example.edu/politicalScience/2015/american-revolution-101/assessment/001/item/001")
                .name("Assessment Item 1")
                .isPartOf(parent)
                .version("1.0")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
                .build())
            .add(AssessmentItem.builder()
                .id("https://example.edu/politicalScience/2015/american-revolution-101/assessment/001/item/002")
                .name("Assessment Item 2")
                .isPartOf(parent)
                .version("1.0")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
                .build())
            .add(AssessmentItem.builder()
                .id("https://example.edu/politicalScience/2015/american-revolution-101/assessment/001/item/003")
                .name("Assessment Item 3")
                .isPartOf(parent)
                .version("1.0")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
                .build())
            .build();
    }
}