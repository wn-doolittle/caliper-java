package org.imsglobal.caliper.entities.outcome;

import org.imsglobal.caliper.entities.assignable.Attempt;

public class Outcome {
    private Attempt attempt;
    private Result result;

    /**
     * Default Constructor
     */
    public Outcome() {

    }

    /**
     * Constructor
     * @param attempt
     * @param result
     */
    public Outcome(Attempt attempt, Result result) {
        this.attempt = attempt;
        this.result = result;
    }

    /**
     * @return attempt
     */
    public Attempt getAttempt() {
        return attempt;
    }

    /**
     * @param attempt
     */
    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    /**
     * @return result
     */
    public Result getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(Result result) {
        this.result = result;
    }
}
