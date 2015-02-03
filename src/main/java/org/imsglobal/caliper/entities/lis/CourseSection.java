package org.imsglobal.caliper.entities.lis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@id", "@type", "semester", "courseNumber", "label", "name", "parentOrg", "dateModified" })
public class CourseSection extends org.imsglobal.caliper.entities.lis.Organization {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("semester")
    private String semester; // TODO - check against LIS Organization

    @JsonProperty("courseNumber")
    private String courseNumber;

    @JsonProperty("label")
    private String label;

    /**
     * @param builder apply builder object properties to the CourseSection object.
     */
    protected CourseSection(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.semester = builder.semester;
        this.courseNumber = builder.courseNumber;
        this.label = builder.label;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @return the courseNumber
     */
    public String getCourseNumber() {
        return courseNumber;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Organization.Builder<T>  {
        private String type;
        private String label;
        private String courseNumber;
        private String semester; // TODO - check against LIS Organization

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Organization.Type.LIS_COURSE_SECTION.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param semester
         * @return semester.
         */
        public T semester(String semester) {
            this.semester = semester;
            return self();
        }

        /**
         * @param courseNumber
         * @return course number.
         */
        public T courseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
            return self();
        }

        /**
         * @param label
         * @return course section label.
         */
        public T label(String label) {
            this.label = label;
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