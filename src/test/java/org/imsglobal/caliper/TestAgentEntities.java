package org.imsglobal.caliper;

import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.agent.SoftwareApplication;

/**
 * Agent entities used to construct Event tests.
 */
public class TestAgentEntities {

    /**
     * Constructor
     */
    public TestAgentEntities() {
        
    }

    /**
     * Build Assessment App.
     * @return edApp
     */
    public static SoftwareApplication buildAssessmentApp() {
        return SoftwareApplication.builder()
            .id("https://com.sat/super-assessment-tool")
            .name("Super Assessment Tool")
            .dateCreated(TestDates.getDefaultDateCreated())
            .build();
    }

    /**
     * Build Media app.
     * @return edApp
     */
    public static SoftwareApplication buildMediaPlayerApp() {
        return SoftwareApplication.builder()
            .id("https://com.sat/super-media-tool")
            .name("Super Media Tool")
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Build Readium app.
     * @return edApp
     */
    public static SoftwareApplication buildReadiumViewerApp() {
        return SoftwareApplication.builder()
            .id("https://github.com/readium/readium-js-viewer")
            .name("Readium")
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Build student 554433.
     * @return person
     */
    public static final Person buildStudent554433() {
        return Person.builder()
            .id("https://some-university.edu/user/554433")
            .hasMemberships(TestLisEntities.buildMemberships())
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }
}