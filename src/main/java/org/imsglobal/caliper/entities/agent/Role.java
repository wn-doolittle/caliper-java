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

package org.imsglobal.caliper.entities.agent;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * IMS LTI/LIS roles.
 */
public enum Role {
    LEARNER("Learner"),
    EXTERNAL_LEARNER("Learner#ExternalLearner"),
    GUEST_LEARNER("Learner#GuestLearner"),
    LEARNER_INSTRUCTOR("Learner#Instructor"),
    LEARNER_LEARNER("Learner#Learner"),
    NONCREDIT_LEARNER("Learner#NonCreditLearner"),

    INSTRUCTOR("Instructor"),
    EXTERNAL_INSTRUCTOR("Instructor#ExternalInstructor"),
    GUEST_INSTRUCTOR("Instructor#GuestInstructor"),
    LECTURER("Instructor#Lecturer"),
    PRIMARY_INSTRUCTOR("Instructor#PrimaryInstructor"),

    ADMINISTRATOR("Administrator"),
    ADMINISTRATOR_ADMINISTRATOR("Administrator#Administrator"),
    ADMINISTRATOR_DEVELOPER("Administrator#Developer"),
    ADMINISTRATOR_SUPPORT("Administrator#Support"),
    ADMINISTRATOR_SYSTEM_ADMINISTRATOR("Administrator#SystemAdministrator"),

    ADMINISTRATOR_EXTERNAL_DEVELOPER("Administrator#ExternalDeveloper"),
    ADMINISTRATOR_EXTERNAL_SUPPORT("Administrator#ExternalSupport"),
    ADMINISTRATOR_EXTERNAL_SYSTEM_ADMINISTRATOR("Administrator#ExternalSystemAdministrator"),

    CONTENT_DEVELOPER("ContentDeveloper"),
    CONTENT_DEVELOPER_CONTENT_DEVELOPER("ContentDeveloper#ContentDeveloper"),
    CONTENT_DEVELOPER_LIBRARIAN("ContentDeveloper#Librarian"),
    CONTENT_DEVELOPER_CONTENT_EXPERT("ContentDeveloper#ContentExpert"),
    CONTENT_DEVELOPER_EXTERNAL_CONTENT_EXPERT("ContentDeveloper#ExternalContentExpert"),

    MANAGER("Manager"),
    MANAGER_AREA_MANAGER("Manager#AreaManager"),
    MANAGER_COURSE_COORDINATOR("Manager#CourseCoordinator"),
    MANAGER_OBSERVER("Manager#Observer"),
    MANAGER_EXTERNAL_OBSERVER("Manager#ExternalObserver"),

    MEMBER("Member"),
    MEMBER_MEMBER("Member#Member"),

    MENTOR("Mentor"),
    MENTOR_MENTOR("Mentor#Mentor"),
    MENTOR_ADVISOR("Mentor#Advisor"),
    MENTOR_AUDITOR("Mentor#Auditor"),
    MENTOR_REVIEWER("Mentor#Reviewer"),
    MENTOR_TUTOR("Mentor#Tutor"),
    MENTOR_LEARNING_FACILITATOR("Mentor#LearningFacilitator"),

    MENTOR_EXTERNAL_MENTOR("Mentor#ExternalMentor"),
    MENTOR_EXTERNAL_ADVISOR("Mentor#ExternalAdvisor"),
    MENTOR_EXTERNAL_AUDITOR("Mentor#ExternalAuditor"),
    MENTOR_EXTERNAL_REVIEWER("Mentor#ExternalReviewer"),
    MENTOR_EXTERNAL_TUTOR("Mentor#ExternalTutor"),
    MENTOR_EXTERNAL_LEARNING_FACILITATOR("Mentor#ExternalLearningFacilitator"),

    TEACHING_ASSISTANT("TeachingAssistant"),
    TEACHING_ASSISTANT_TEACHING_ASSISTANT("TeachingAssistant#TeachingAssistant"),
    TEACHING_ASSISTANT_GRADER("TeachingAssistant#Grader"),
    TEACHING_ASSISTANT_TEACHING_ASSISTANT_SECTION("TeachingAssistant#TeachingAssistantSection"),
    TEACHING_ASSISTANT_TEACHING_ASSISTANT_SECTION_ASSOCIATION("TeachingAssistant#TeachingAssistantSectionAssociation"),
    TEACHING_ASSISTANT_TEACHING_ASSISTANT_OFFERING("TeachingAssistant#TeachingAssistantOffering"),
    TEACHING_ASSISTANT_TEACHING_ASSISTANT_TEMPLATE("TeachingAssistant#TeachingAssistantTemplate"),
    TEACHING_ASSISTANT_TEACHING_ASSISTANT_GROUP("TeachingAssistant#TeachingAssistantGroup");

    private final String value;
    private static Map<String, Role> lookup;

    /**
     * Create reverse lookup hash map
     */
    static {
        Map<String, Role> map = new HashMap<String, Role>();
        for (Role constants : Role.values()) {
            map.put(constants.value(), constants);
        }
        lookup = ImmutableMap.copyOf(map);
    }

    /**
     * Private constructor
     * @param value
     */
    private Role(final String value) {
        this.value = value;
    }

    /**
     * @param key
     * @return true if lookup returns a key match; false otherwise.
     */
    public static boolean hasKey(String key) {
        return lookup.containsKey(key);
    }

    /**
     * @return the URI value
     */
    @JsonValue
    public String value() {
        return value;
    }
}