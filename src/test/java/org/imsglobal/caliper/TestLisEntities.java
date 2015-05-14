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

import org.imsglobal.caliper.entities.lis.CourseOffering;
import org.imsglobal.caliper.entities.lis.CourseSection;
import org.imsglobal.caliper.entities.lis.Group;
import org.imsglobal.caliper.entities.lis.Membership;
import org.imsglobal.caliper.entities.lis.Role;
import org.imsglobal.caliper.entities.lis.Status;

/**
 * LIS entities used in construction of Event tests.
 */
public class TestLisEntities {

    /**
     * Constructor
     */
    public TestLisEntities() {

    }

    /**
     * Build sample Course offereing.
     * @return course offering
     */
    public static final CourseOffering buildCourseOffering() {
        return CourseOffering.builder()
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101")
            .courseNumber("POL101")
            .name("Political Science 101: The American Revolution")
            .academicSession("Fall-2015")
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Build sample CourseSection including parent course.
     * @return course section
     */
    public static final CourseSection buildCourseSection() {
        return CourseSection.builder()
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001")
            .courseNumber("POL101")
            .name("American Revolution 101")
            .academicSession("Fall-2015")
            .subOrganizationOf(buildCourseOffering())
            .dateCreated(TestDates.getDefaultDateCreated())
            .dateModified(TestDates.getDefaultDateModified())
            .build();
    }

    /**
     * Build Event.group, in this case Am Rev 101 course offering, course section 001, group 001.
     * @return group
     */
    public static final Group buildGroup() {
        return Group.builder()
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001/group/001")
            .name("Discussion Group 001")
            .subOrganizationOf(buildCourseSection())
            .dateCreated(TestDates.getDefaultDateCreated())
            .build();
    }

    /**
     * Build sample Course Section membership.
     * @return membership
     */
    public static final Membership buildMembership() {
        return Membership.builder()
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101/roster/554433")
            .name("American Revolution 101")
            .description("Roster entry")
            .member(TestAgentEntities.buildStudent554433())
            .organization(buildCourseSection())
            .role(Role.LEARNER)
            .status(Status.ACTIVE)
            .dateCreated(TestDates.getDefaultDateCreated())
            .build();
    }
}