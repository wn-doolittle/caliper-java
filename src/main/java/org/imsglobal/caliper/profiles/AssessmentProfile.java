package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assessment.CaliperAssessment;
import org.imsglobal.caliper.entities.assessment.CaliperAssessmentItem;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class AssessmentProfile extends BaseProfile {

    public enum AssessmentAction {
        STARTED("assessment.started"),
        PAUSED("assessment.paused"),
        RESTARTED("assessment.restarted"),
        SUBMITTED("assessment.submitted");

        private final String key;
        private static final Map<String, AssessmentAction> lookup = new HashMap<String, AssessmentAction>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (AssessmentAction constants : AssessmentAction.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private AssessmentAction(String key) {
            this.key = key;
        }

        /**
         * @return ResourceBundle key for internationalized action strings.
         */
        public String key() {
            return key;
        }

        /**
         * @param key
         * @return true if lookup returns a key match; false otherwise.
         */
        public static boolean hasKey(String key) {
            return lookup.containsKey(key);
        }

        /**
         * @param key
         * @return enum constant by reverse lookup
         */
        public static AssessmentAction lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    public enum AssessmentItemAction {
        STARTED("assessment.item.started"),
        COMPLETED("assessment.item.completed"),
        SKIPPED("assessment.item.skipped"),
        REVIEWED("assessment.item.reviewed"),
        VIEWED("assessment.item.viewed");

        private final String key;
        private static final Map<String, AssessmentItemAction> lookup = new HashMap<String, AssessmentItemAction>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (AssessmentItemAction constants : AssessmentItemAction.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private AssessmentItemAction(String key) {
            this.key = key;
        }

        /**
         * @return ResourceBundle key for internationalized action strings.
         */
        public String key() {
            return key;
        }

        /**
         * @param key
         * @return true if lookup returns a key match; false otherwise.
         */
        public static boolean hasKey(String key) {
            return lookup.containsKey(key);
        }

        /**
         * @param key
         * @return enum constant by reverse lookup
         */
        public static AssessmentItemAction lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    private CaliperAssessment assessment;
    private List<CaliperAssessmentItem> assessmentItem = new ArrayList<CaliperAssessmentItem>();

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected AssessmentProfile(Builder<?> builder) {
        super(builder);
        this.assessment = builder.assessment;
        this.assessmentItem = builder.assessmentItem;
    }

    /**
     * @return Assessment
     */
    public CaliperAssessment getAssessment() {
        return assessment;
    }

    /**
     * @return List of assessment items
     */
    public List<CaliperAssessmentItem>  getAssessmentItem() {
        return assessmentItem;
    }

    /**
     * Initialize default parameter values in the builder (not in the outer profile class).
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseProfile.Builder<T>  {
        private CaliperAssessment assessment;
        private List<CaliperAssessmentItem> assessmentItem = new ArrayList<CaliperAssessmentItem>();

        /**
         * @param assessment
         * @return builder.
         */
        public T assessment(CaliperAssessment assessment) {
            this.assessment = assessment;
            return self();
        }

        /**
         * @param assessmentItem
         * @return builder.
         */
        public T assessmentItem(List<CaliperAssessmentItem> assessmentItem) {
            this.assessmentItem = assessmentItem;
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
}