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
            .id("https://example.com/super-assessment-tool")
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
            .id("https://example.com/super-media-tool")
            .name("Super Media Tool")
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Build ePub app.
     * @return edApp
     */
    public static SoftwareApplication buildEpubViewerApp() {
        return SoftwareApplication.builder()
            .id("https://example.com/viewer")
            .name("ePub")
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
            .id("https://example.edu/user/554433")
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }
}