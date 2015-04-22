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
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101/assessment1")
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
            .maxScore(3) // WARN original value "5.0d"
            .assessmentItems(buildAssessmentItems(Assessment.builder()
                .id("https://some-university.edu/politicalScience/2015/american-revolution-101/assessment1")
                .build()))
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
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
                .id("https://some-university.edu/politicalScience/2015/american-revolution-101/assessment1/item1")
                .name("Assessment Item 1")
                .isPartOf(parent)
                .version("1.0")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
                .build())
            .add(AssessmentItem.builder()
                .id("https://some-university.edu/politicalScience/2015/american-revolution-101/assessment1/item2")
                .name("Assessment Item 2")
                .isPartOf(parent)
                .version("1.0")
                .maxAttempts(2)
                .maxSubmits(2)
                .maxScore(1)
                .isTimeDependent(false)
                .build())
            .add(AssessmentItem.builder()
                .id("https://some-university.edu/politicalScience/2015/american-revolution-101/assessment1/item3")
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