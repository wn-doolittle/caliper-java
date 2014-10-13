package org.imsglobal.caliper.entities.lis;

public class LISCourseSection extends LISOrganization {

    private String semester; // TODO - check against LIS LISOrganization
    private String courseNumber;
    private String label;

    /**
     * @param builder apply builder object properties to the LISCourseSection object.
     */
    protected LISCourseSection(Builder<?> builder) {
        super(builder);
        this.semester = builder.semester;
        this.courseNumber = builder.courseNumber;
        this.label = builder.label;
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
    public static abstract class Builder<T extends Builder<T>> extends LISOrganization.Builder<T>  {
        private String label;
        private String courseNumber;
        private String semester; // TODO - check against LIS LISOrganization

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(LISOrganization.Type.LIS_COURSE_SECTION.uri());
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
         * @return a new instance of the LISCourseSection.
         */
        public LISCourseSection build() {
            return new LISCourseSection(this);
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