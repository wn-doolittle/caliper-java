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

import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.lis.CourseOffering;
import org.imsglobal.caliper.entities.lis.CourseSection;
import org.imsglobal.caliper.entities.lis.Group;
import org.imsglobal.caliper.entities.lis.Role;
import org.imsglobal.caliper.entities.lis.Status;
import org.imsglobal.caliper.entities.w3c.Membership;

import java.util.List;

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
     * Build Event.group, in this case Am Rev 101 course offering, course section 001, group 001.
     * @return group
     */
    public static final Group buildGroup() {
        return Group.builder()
            .id("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001/group/001")
            .name("Discussion Group 001")
            .membership(org.imsglobal.caliper.entities.lis.Membership.builder()
                .id("https://some-university.edu/membership/003")
                .memberId("https://some-university.edu/user/554433")
                .organizationId("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001/group/001")
                .role(Role.LEARNER)
                .status(Status.ACTIVE)
                .dateCreated(TestDates.getDefaultDateCreated())
                .build())
            .subOrganizationOf(CourseSection.builder()
                .id("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001")
                .courseNumber("POL101")
                .name("American Revolution 101")
                .academicSession("Fall-2015")
                .membership(org.imsglobal.caliper.entities.lis.Membership.builder()
                    .id("https://some-university.edu/membership/002")
                    .memberId("https://some-university.edu/user/554433")
                    .organizationId("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001")
                    .role(Role.LEARNER)
                    .status(Status.ACTIVE)
                    .dateCreated(TestDates.getDefaultDateCreated())
                    .build())
                .subOrganizationOf(CourseOffering.builder()
                    .id("https://some-university.edu/politicalScience/2015/american-revolution-101")
                    .courseNumber("POL101")
                    .name("Political Science 101: The American Revolution")
                    .academicSession("Fall-2015")
                    .dateCreated(TestDates.getDefaultDateCreated())
                    .dateModified(TestDates.getDefaultDateModified())
                    .build())
                .dateCreated(TestDates.getDefaultDateCreated())
                .dateModified(TestDates.getDefaultDateModified())
                .build())
            .dateCreated(TestDates.getDefaultDateCreated())
            .build();
    }

    /**
     * Build Memberships
     * @return membership list
     */
    public static final List<Membership> buildMemberships() {
        List<org.imsglobal.caliper.entities.w3c.Membership> memberships = Lists.newArrayList();
        memberships.add(org.imsglobal.caliper.entities.lis.Membership.builder()
            .id("https://some-university.edu/membership/001")
            .memberId("https://some-university.edu/user/554433")
            .organizationId("https://some-university.edu/politicalScience/2015/american-revolution-101")
            .role(Role.LEARNER)
            .status(Status.ACTIVE)
            .dateCreated(TestDates.getDefaultDateCreated())
            .build());
        memberships.add(org.imsglobal.caliper.entities.lis.Membership.builder()
            .id("https://some-university.edu/membership/002")
            .memberId("https://some-university.edu/user/554433")
            .organizationId("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001")
            .role(Role.LEARNER)
            .status(Status.ACTIVE)
            .dateCreated(TestDates.getDefaultDateCreated())
            .build());
        memberships.add(org.imsglobal.caliper.entities.lis.Membership.builder()
            .id("https://some-university.edu/membership/003")
            .memberId("https://some-university.edu/user/554433")
            .organizationId("https://some-university.edu/politicalScience/2015/american-revolution-101/section/001/group/001")
            .role(Role.LEARNER)
            .status(Status.ACTIVE)
            .dateCreated(TestDates.getDefaultDateCreated())
            .build());

        return memberships;
    }
}