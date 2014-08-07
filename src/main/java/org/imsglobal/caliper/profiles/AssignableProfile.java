package org.imsglobal.caliper.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.actions.AssignableActions;

import java.util.Date;
import java.util.ResourceBundle;

@JsonPropertyOrder({
    "dateCreated",
    "datePublished",
    "dateToStartOn",
    "dateToShow",
    "dateToSubmit",
    "maxAttempts",
    "maxSubmits",
    "action" })
public class AssignableProfile extends BaseProfile {

    @JsonProperty("dateCreated")
    private Date dateCreated;

    @JsonProperty("datePublished")
    private Date datePublished;

    @JsonProperty("dateToStartOn")
    private Date dateToStartOn;

    @JsonProperty("dateToShow")
    private Date dateToShow;

    @JsonProperty("dateToSubmit")
    private Date dateToSubmit;

    @JsonProperty("maxAttempts")
    private int maxAttempts;

    @JsonProperty("maxSubmits")
    private int maxSubmits;

    @JsonProperty("action")
    private String action;

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
        this.action = builder.action;
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
     * @return action.
     */
    public String getAction() {
        return action;
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
        private String action;

        /**
         * @param dateCreated
         * @return builder.
         */
        private T dateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return self();
        }

        /**
         * @param datePublished
         * @return builder.
         */
        private T datePublished(Date datePublished) {
            this.datePublished = datePublished;
            return self();
        }

        /**
         * @param dateToStartOn
         * @return builder.
         */
        private T dateToStartOn(Date dateToStartOn) {
            this.dateToStartOn = dateToStartOn;
            return self();
        }

        /**
         * @param dateToShow
         * @return builder.
         */
        private T dateToShow(Date dateToShow) {
            this.dateToShow = dateToShow;
            return self();
        }

        /**
         * @param dateToSubmit
         * @return builder.
         */
        private T dateToSubmit(Date dateToSubmit) {
            this.dateToSubmit = dateToSubmit;
            return self();
        }

        /**
         * @param maxAttempts
         * @return builder.
         */
        private T maxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return self();
        }

        /**
         * @param maxSubmits
         * @return builder.
         */
        private T maxSubmits(int maxSubmits) {
            this.maxSubmits = maxSubmits;
            return self();
        }

        /**
         * @param key
         * @return builder after validating action key.
         */
        public T action(String key) {
            this.action = validateAction(key);
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

    /**
     * @param key resource bundle key attribute of target constant
     * @return resource bundle key
     */
    private static String validateAction(String key) {
        if (AssignableActions.hasKey(key)) {
            return ResourceBundle.getBundle("resources.actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized constant");
        }
    }
}