package org.imsglobal.caliper.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.imsglobal.caliper.actions.AssessmentActions;
import org.imsglobal.caliper.actions.AssessmentItemActions;

@JsonPropertyOrder({
    "maxSubmits",
    "submissionCount",
    "maxTimeAllowed",
    "timeTaken",
    "action" })
public class AssessmentProfile extends BaseProfile {
    
    @JsonProperty("maxSubmits")
    private int maxSubmits;  // dup assignable

    @JsonProperty("submissionCount")
    private int submissionCount;

    @JsonProperty("maxTimeAllowed")  // move to assignable
    private int maxTimeAllowed;

    @JsonProperty("timeTaken")
    private int timeTaken;

    @JsonProperty("action")
    private String action;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssessmentProfile(Builder<?> builder) {
        super(builder);
        this.maxSubmits = builder.maxSubmits;
        this.submissionCount = builder.submissionCount;
        this.maxTimeAllowed = builder.maxTimeAllowed;
        this.timeTaken = builder.timeTaken;
        this.action = builder.action;
    }

    /**
     * @return maximum number of submissions allowed.
     */
    public int getmaxSubmits() {
        return maxSubmits;
    }

    /**
     * @return current submission count.
     */
    public int getSubmissionCount() {
        return submissionCount;
    }

    /**
     * @return maximum time allowed for the assessment.
     */
    public int getMaxTimeAllowed() {
        return maxTimeAllowed;
    }

    /**
     * @return time taken.
     */
    public int getTimeTaken() {
        return timeTaken;
    }

    /**
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private int maxSubmits;
        private int submissionCount;
        private int maxTimeAllowed;
        private int timeTaken;
        private List<String> assets = new ArrayList<String>();
        private String action;

        /**
         * @param maxSubmits
         * @return builder.
         */
        public T maxSubmits(int maxSubmits) {
            this.maxSubmits = maxSubmits;
            return self();
        }

        /**
         * @param submissionCount
         * @return builder.
         */
        public T submissionCount(int submissionCount) {
            this.submissionCount = submissionCount;
            return self();
        }

        /**
         * TODO time interval: Fractional hours, minutes?
         * @param maxTimeAllowed
         * @return builder.
         */
        public T maxTimeAllowed(int maxTimeAllowed) {
            this.maxTimeAllowed = maxTimeAllowed;
            return self();
        }

        /**
         * @param timeTaken
         * @return builder.
         */
        public T timeTaken(int timeTaken) {
            this.timeTaken = timeTaken;
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
         * Client invokes build method in order to create an immutable profile object.
         * @return a new instance of the AssessmentProfile.
         */
        public AssessmentProfile build() {
            return new AssessmentProfile(this);
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
        if (AssessmentActions.hasKey(key) || AssessmentItemActions.hasKey(key)) {
            return ResourceBundle.getBundle("resources.actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized constant");
        }
    }
}