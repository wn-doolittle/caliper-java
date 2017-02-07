package org.imsglobal.caliper.entities.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.annotation.Nullable;

/**
 * Reusable, composable object for decorating an AssignableDigitalResource with assignment-related properties.
 */
public class Assignment {

    @JsonProperty("dateToActivate")
    private DateTime dateToActivate;

    @JsonProperty("dateToShow")
    private DateTime dateToShow;

    @JsonProperty("dateToStartOn")
    private DateTime dateToStartOn;

    @JsonProperty("dateToSubmit")
    private DateTime dateToSubmit;

    @JsonProperty("maxAttempts")
    private int maxAttempts;

    @JsonProperty("maxSubmits")
    private int maxSubmits;

    @JsonProperty("maxScore")
    private double maxScore;

    /**
     * Constructor
     */
    public Assignment() {

    }

    /**
     * @return the dateToActivate
     */
    @Nullable
    public DateTime getDateToActivate() {
        return dateToActivate;
    }

    /**
     * Set the activation date for this assignment.
     * @param dateToActivate
     */
    public void setDateToActivate(DateTime dateToActivate) {
        this.dateToActivate = dateToActivate;
    }

    /**
     * @return the dateToShow
     */
    @Nullable
    public DateTime getDateToShow() {
        return dateToShow;
    }

    /**
     * Set show date for this assignment
     * @param dateToShow
     */
    public void setDateToShow(DateTime dateToShow) {
        this.dateToShow = dateToShow;
    }

    /**
     * @return the dateToStartOn
     */
    @Nullable
    public DateTime getDateToStartOn() {
        return dateToStartOn;
    }

    /**
     * Set the start date for this assignment.
     * @param dateToStartOn
     */
    public void setDateToStartOn(DateTime dateToStartOn) {
        this.dateToStartOn = dateToStartOn;
    }

    /**
     * @return the dateToSubmit
     */
    @Nullable
    public DateTime getDateToSubmit() {
        return dateToSubmit;
    }

    /**
     * Set the submission date for this assignment
     * @param dateToSubmit
     */
    public void setDateToSubmit(DateTime dateToSubmit) {
        this.dateToSubmit = dateToSubmit;
    }

    /**
     * @return the maxAttempts
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Set the maximum attempts permitted for this assignment.
     * @param maxAttempts
     */
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    /**
     * @return the maxSubmits
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int getMaxSubmits() {
        return maxSubmits;
    }

    /**
     * Set the maximum number of submissions permitted for this assignment.
     * @param maxSubmits
     */
    public void setMaxSubmits(int maxSubmits) {
        this.maxSubmits = maxSubmits;
    }

    /**
     * @return the maxScore
     */
    @Nullable
    // @JsonSerialize(using=JsonDoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getMaxScore() {
        return maxScore;
    }

    /**
     * Set the maximum score permitted for this assignment.
     * @param maxScore
     */
    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }
}