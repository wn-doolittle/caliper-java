/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
    private Integer maxAttempts;

    @JsonProperty("maxSubmits")
    private Integer maxSubmits;

    @JsonProperty("maxScore")
    private Double maxScore;

    @JsonProperty("correctResponse")
    private String correctResponse;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Set the maximum attempts permitted for this assignment.
     * @param maxAttempts
     */
    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    /**
     * @return the maxSubmits
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Integer getMaxSubmits() {
        return maxSubmits;
    }

    /**
     * Set the maximum number of submissions permitted for this assignment.
     * @param maxSubmits
     */
    public void setMaxSubmits(Integer maxSubmits) {
        this.maxSubmits = maxSubmits;
    }

    /**
     * @return the maxScore
     */
    @Nullable
    // @JsonSerialize(using=DoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Double getMaxScore() {
        return maxScore;
    }

    /**
     * Set the maximum score permitted for this assignment.
     * @param maxScore
     */
    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * @return the correctResponse
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getCorrectResponse() {
        return correctResponse;
    }

    public void setCorrectResponse(String correctResponse) {
        this.correctResponse = correctResponse;
    }
}
