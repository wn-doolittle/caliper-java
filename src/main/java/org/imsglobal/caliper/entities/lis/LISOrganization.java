package org.imsglobal.caliper.entities.lis;

import org.imsglobal.caliper.entities.CaliperAgent;

public class LISOrganization extends CaliperAgent {

    public enum Type {
        LIS_COURSE_SECTION("http://purl.imsglobal.org/caliper/v1/LISCourseSection");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Type(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    private final String type;
    private String title;
    private LISOrganization parentOrg;

    /**
     * @param builder apply builder object properties to the LISOrganization object.
     */
    protected LISOrganization(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.title = builder.title;
        this.parentOrg = builder.parentOrg;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return parent organization.
     */
    public LISOrganization getParentOrg() {
        return parentOrg;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperAgent.Builder<T>  {
        private String type;
        private String title;
        private LISOrganization parentOrg;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperAgent.Type.LIS_ORGANIZATION.uri());
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
         * @param title
         * @return builder.
         */
        public T title(String title) {
            this.title = title;
            return self();
        }

        /**
         * @param parentOrg
         * @return builder.
         */
        public T parentOrg(LISOrganization parentOrg) {
            this.parentOrg = parentOrg;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the LISOrganization.
         */
        public LISOrganization build() {
            return new LISOrganization(this);
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