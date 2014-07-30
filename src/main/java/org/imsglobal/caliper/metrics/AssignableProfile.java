package org.imsglobal.caliper.metrics;

import java.util.Date;

public class AssignableProfile extends BaseProfile {
    private Date dateCreated;
    private Date datePublished;
    private Date dateToStartOn;
    private Date dateToShow;
    private Date dateToSubmit;
    private int maxAttempts;
    private int maxSubmits;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssignableProfile(Builder<?> builder) {
        super(builder);
        this.dateCreated = builder.dateCreated;
        this.datePublished = builder.datePublished;
        this.dateToStartOn = builder.dateToStartOn;
        this.dateToShow = builder.dateToShow;
        this.dateToSubmit = builder.dateToSubmit;
        this.maxAttempts = builder.maxAttempts;
        this.maxSubmits = builder.maxSubmits;
    }

    /**
     * @return creation date.
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @return publication date.
     */
    public Date getDatePublished() {
        return datePublished;
    }

    /**
     * @return start date.
     */
    public Date getDateToStartOn() {
        return dateToStartOn;
    }

    /**
     * @return show date.
     */
    public Date getDateToShow() {
        return dateToShow;
    }

    /**
     * @return submission date.
     */
    public Date getDateToSubmit() {
        return dateToSubmit;
    }

    /**
     * @return max attempts permitted.
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * @return max submissions permitted.
     */
    public int getMaxSubmits() {
        return maxSubmits;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private Date dateCreated;
        private Date datePublished;
        private Date dateToStartOn;
        private Date dateToShow;
        private Date dateToSubmit;
        private int maxAttempts;
        private int maxSubmits;

        /**
         * @param dateCreated
         * @return creation date.
         */
        private T dateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return self();
        }

        /**
         * @param datePublished
         * @return publication date.
         */
        private T datePublished(Date datePublished) {
            this.datePublished = datePublished;
            return self();
        }

        /**
         * @param dateToStartOn
         * @return start date.
         */
        private T dateToStartOn(Date dateToStartOn) {
            this.dateToStartOn = dateToStartOn;
            return self();
        }

        /**
         * @param dateToShow
         * @return show date.
         */
        private T dateToShow(Date dateToShow) {
            this.dateToShow = dateToShow;
            return self();
        }

        /**
         * @param dateToSubmit
         * @return submission date.
         */
        private T dateToSubmit(Date dateToSubmit) {
            this.dateToSubmit = dateToSubmit;
            return self();
        }

        /**
         * @param maxAttempts
         * @return max attempts permitted.
         */
        private T maxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return self();
        }

        /**
         * @param maxSubmits
         * @return max submissions permitted.
         */
        private T maxSubmits(int maxSubmits) {
            this.maxSubmits = maxSubmits;
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public AssignableProfile build() {
            return new AssignableProfile(this);
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
