package org.imsglobal.caliper.entities.outcome;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.CaliperEntity;

/**
 * Representation of a Result. Result's are generated as
 * part of an interaction represented by an OutcomeEvent.
 */
public class Result extends CaliperEntity {

    private final String type;
    private double normalScore, penaltyScore, extraCreditScore, totalScore, curvedTotalScore, curveFactor;
    private String comment;
    private CaliperAgent scoredBy;
    // TODO - need to include target, learningObjective and scoreConstraints from metric profile

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the normalScore
     */
    public double getNormalScore() {
        return normalScore;
    }

    /**
     * @return the penaltyScore
     */
    public double getPenaltyScore() {
        return penaltyScore;
    }

    /**
     * @return the extraCreditScore
     */
    public double getExtraCreditScore() {
        return extraCreditScore;
    }

    /**
     * @return the totalScore
     */
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * @return the curvedTotalScore
     */
    public double getCurvedTotalScore() {
        return curvedTotalScore;
    }

    /**
     * @return the curveFactor
     */
    public double getCurveFactor() {
        return curveFactor;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the agent who scored the result
     */
    public CaliperAgent getScoredBy() {
        return scoredBy;
    }

    /**
     * @param builder apply builder object properties to the Result object.
     */
    protected Result(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.normalScore = builder.normalScore;
        this.penaltyScore = builder.penaltyScore;
        this.extraCreditScore = builder.extraCreditScore;
        this.totalScore = builder.totalScore;
        this.curvedTotalScore = builder.curvedTotalScore;
        this.curveFactor = builder.curveFactor;
        this.comment = builder.comment;
        this.scoredBy = builder.scoredBy;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEntity.Builder<T>  {
        private static final String RESULT_TYPE = "http://purl.imsglobal.org/caliper/v1/Result";
        private double normalScore, penaltyScore, extraCreditScore, totalScore, curvedTotalScore, curveFactor;
        private String type, comment;
        private CaliperAgent scoredBy;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(RESULT_TYPE);
        }

        /**
         * @param type
         * @return the IMS Global type reference URI.
         */
        @Override
        public T type(String type) {
            if (type.equals(RESULT_TYPE)) {
                this.type = type;
            } else {
                this.type = RESULT_TYPE;
            }
            return self();
        }

        /**
         * @param normalScore
         * @return normal score.
         */
        public T normalScore(double normalScore) {
            this.normalScore = normalScore;
            return self();
        }

        /**
         * @param penaltyScore
         * @return penalty score.
         */
        public T penaltyScore(double penaltyScore) {
            this.penaltyScore = penaltyScore;
            return self();
        }

        /**
         * @param extraCreditScore
         * @return extra credit score.
         */
        public T extraCreditScore(double extraCreditScore) {
            this.extraCreditScore = extraCreditScore;
            return self();
        }

        /**
         * @param totalScore
         * @return total score.
         */
        public T totalScore(double totalScore) {
            this.totalScore = totalScore;
            return self();
        }

        /**
         * @param curvedTotalScore
         * @return curved total score.
         */
        public T curvedTotalScore(double curvedTotalScore) {
            this.curvedTotalScore = curvedTotalScore;
            return self();
        }

        /**
         * @param curveFactor
         * @return curve factor.
         */
        public T curveFactor(double curveFactor) {
            this.curveFactor = curveFactor;
            return self();
        }

        /**
         * @param comment
         * @return comment.
         */
        public T comment(String comment) {
            this.comment = comment;
            return self();
        }

        /**
         * @param scoredBy
         * @return agent who scored the result.
         */
        public T scoredBy(CaliperAgent scoredBy) {
            this.scoredBy = scoredBy;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Result.
         */
        public Result build() {
            return new Result(this);
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