package org.imsglobal.caliper.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.actions.OutcomeActions;
import java.util.ResourceBundle;

@JsonPropertyOrder({
    "normalScore",
    "penaltyScore",
    "extraCreditScore",
    "totalScore",
    "curveFactor",
    "ScoreConstraints",
    "comment",
    "learningObjectiveResult",
    "action" })
public class OutcomeProfile extends BaseProfile {

    @JsonProperty("normalScore")
    private double normalScore;

    @JsonProperty("penaltyScore")
    private double penaltyScore;

    @JsonProperty("extraCreditScore")
    private double extraCreditScore;

    @JsonProperty("totalScore")
    private double totalScore;

    @JsonProperty("curveFactor")
    private double curveFactor;

    @JsonProperty("scoreConstraints")
    private Object scoreConstraints;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("learningObjectiveResult")
    private Object learningObjectiveResult;

    @JsonProperty("action")
    private String action;

    /**
     * @param builder apply builder object properties to the profile object.
     */
    protected OutcomeProfile(Builder<?> builder) {
        super(builder);
        this.normalScore = builder.normalScore;
        this.penaltyScore = builder.penaltyScore;
        this.extraCreditScore = builder.extraCreditScore;
        this.totalScore = builder.totalScore;
        this.curveFactor = builder.curveFactor;
        this.scoreConstraints = builder.scoreConstraints;
        this.comment = builder.comment;
        this.learningObjectiveResult = builder.learningObjectiveResult;
        this.action = builder.action;
    }

    /**
     * @return score earned by the learner before adding extra credit or subtracting penalties.
     */
    public double getNormalScore() {
        return normalScore;
    }

    /**
     * @return number of point deducted from the normal score due to some penalty such
     * as submitting an assignment after the due date.
     */
    public double getPenaltyScore() {
        return penaltyScore;
    }

    /**
     * @return number of extra credit points earned by the learner.
     */
    public double getExtraCreditScore() {
        return extraCreditScore;
    }

    /**
     * @return total score on the assignment (totalScore=normalScore + extraCreditScore - penalty)
     */
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * @return curve adjusted total score.
     */
    public double getCurveFactor() {
        return curveFactor;
    }

    /**
     * @return score constraints.
     */
    public Object getScoreConstraints() {
        return scoreConstraints;
    }

    /**
     * @return comment about the result suitable for display to the learner.
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return learning objective met by the result.
     */
    public Object getLearningObjectiveResult() {
        return learningObjectiveResult;
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
        private double normalScore;
        private double penaltyScore;
        private double extraCreditScore;
        private double totalScore;
        private double curveFactor;
        private Object scoreConstraints;
        private String comment;
        private Object learningObjectiveResult;
        private String action;

        /**
         * @param normalScore
         * @return builder.
         */
        private T normalScore(double normalScore) {
            this.normalScore = normalScore;
            return self();
        }

        /**
         * @param penaltyScore
         * @return builder.
         */
        private T penaltyScore(double penaltyScore) {
            this.penaltyScore = penaltyScore;
            return self();
        }

        /**
         * @param extraCreditScore
         * @return builder.
         */
        private T extraCreditScore(double extraCreditScore) {
            this.extraCreditScore = extraCreditScore;
            return self();
        }

        /**
         * @param totalScore
         * @return builder.
         */
        private T totalScore(double totalScore) {
            this.totalScore = totalScore;
            return self();
        }

        /**
         * @param curveFactor
         * @return builder.
         */
        private T curveFactor(double curveFactor) {
            this.curveFactor = curveFactor;
            return self();
        }

        /**
         * @param scoreConstraints
         * @return builder.
         */
        private T scoreConstraints(Object scoreConstraints) {
            this.scoreConstraints = scoreConstraints;
            return self();
        }

        /**
         * @param comment
         * @return builder.
         */
        private T comment(String comment) {
            this.comment = comment;
            return self();
        }

        /**
         * @param learningObjectiveResult
         * @return builder.
         */
        private T learningObjectiveResult(Object learningObjectiveResult) {
            this.learningObjectiveResult = learningObjectiveResult;
            return self();
        }

        /**
         * @param key
         * @return action verb from the actions resource bundle.
         */
        public T action(String key) {
            this.action = validateAction(key);
            return self();
        }

        /**
         * Client invokes the build method in order to create an immutable profile object.
         * @return a new instance of MediaProfile.
         */
        public OutcomeProfile build() {
            return new OutcomeProfile(this);
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
        if (OutcomeActions.hasKey(key)) {
            return ResourceBundle.getBundle("resources.actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized constant");
        }
    }
}