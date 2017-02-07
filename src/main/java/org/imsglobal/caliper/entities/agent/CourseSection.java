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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;

import javax.annotation.Nullable;

/**
 * A CourseSection is a way to represent a group of people associated with a course or class.
 * These groups may include everyone in the class or course, or may be subsets of that whole group.
 * CourseSections may have sub-sections (these are created as separate Group objects linked using
 * the relationship). Examples of a CourseSection are Lecture, Laboratory, Studio, Seminar, etc.
 * There may be several instances of a type of CourseSection e.g., multiple lectures.
 *
 * A Caliper CourseSection provides a subset of the CourseSection properties specified in the
 * IMS LTI 2.0 specification, which in turn, draws inspiration from the IMS LIS 1.0 specification.
 */
public class CourseSection extends AbstractEntity implements Course {

    @JsonProperty("courseNumber")
    private final String courseNumber;

    @JsonProperty("academicSession")
    private final String academicSession;

    @JsonProperty("category")
    private final String category;

    @JsonProperty("subOrganizationOf")
    private final Organization subOrganizationOf;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected CourseSection(Builder<?> builder) {
        super(builder);

        this.courseNumber = builder.courseNumber;
        this.academicSession = builder.academicSession;
        this.category = builder.category;
        this.subOrganizationOf = builder.subOrganizationOf;
    }

    /**
     * The course number, such as "Biology 101". In general, this number is not simply a numeric value.
     * @return the course number.
     */
    @Nullable
    public String getCourseNumber() {
        return courseNumber;
    }

    /**
     * @return academic session
     */
    @Nullable
    public String getAcademicSession() {
        return academicSession;
    }

    /**
     * @return category.
     */
    @Nullable
    public String getCategory() {
        return category;
    }

    /**
     * @return the parent organization.
     */
    @Nullable
    public Organization getSubOrganizationOf() {
        return subOrganizationOf;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {
        private String courseNumber;
        private String academicSession;
        private String category;
        private Organization subOrganizationOf;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.COURSE_SECTION);
        }

        /**
         * @param courseNumber
         * @return builder.
         */
        public T courseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
            return self();
        }

        /**
         * @param academicSession
         * @return builder.
         */
        public T academicSession(String academicSession) {
            this.academicSession = academicSession;
            return self();
        }

        /**
         * @param category
         * @return builder.
         */
        public T category(String category) {
            this.category = category;
            return self();
        }

        /**
         * @param subOrganizationOf
         * @return builder.
         */
        public T subOrganizationOf(Organization subOrganizationOf) {
            this.subOrganizationOf = subOrganizationOf;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the CourseSection.
         */
        public CourseSection build() {
            return new CourseSection(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}