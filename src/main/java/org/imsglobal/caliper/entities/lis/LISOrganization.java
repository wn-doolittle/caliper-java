package org.imsglobal.caliper.entities.lis;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.CaliperEntity;

public class LISOrganization extends CaliperEntity implements CaliperAgent {

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
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private static final String LISORG_TYPE = "http://purl.imsglobal.org/caliper/v1/LISOrganization";
        private String type;
        private String title;
        private LISOrganization parentOrg;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(LISORG_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(LISORG_TYPE)) {
                this.type = type;
            } else {
                this.type = LISORG_TYPE;
            }
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
