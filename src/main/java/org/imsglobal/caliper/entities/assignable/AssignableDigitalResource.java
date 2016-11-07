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

package org.imsglobal.caliper.entities.assignable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.BaseEntity;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.LearningObjective;
import org.imsglobal.caliper.entities.agent.Agent;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Assignable Digital Resource
 */
public class AssignableDigitalResource extends BaseEntity implements Assignable {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("mediaType")
    private final String mediaType;

    @JsonProperty("creators")
    private final ImmutableList<Agent> creators;

    @JsonProperty("learningObjectives")
    private final ImmutableList<LearningObjective> learningObjectives;

    @JsonProperty("keywords")
    private final ImmutableList<String> keywords;

    @JsonProperty("isPartOf")
    private final Entity isPartOf;

    @JsonProperty("datePublished")
    private final DateTime datePublished;

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

    @JsonProperty("version")
    private final String version;

    /**
     * @param builder apply builder object properties to the Target object.
     */
    protected AssignableDigitalResource(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.mediaType = builder.mediaType;
        this.creators = ImmutableList.copyOf(builder.creators);
        this.learningObjectives = ImmutableList.copyOf(builder.learningObjectives);
        this.keywords = ImmutableList.copyOf(builder.keywords);
        this.isPartOf = builder.isPartOf;
        this.datePublished = builder.datePublished;
        this.dateToActivate = builder.dateToActivate;
        this.dateToShow = builder.dateToShow;
        this.dateToStartOn = builder.dateToStartOn;
        this.dateToSubmit = builder.dateToSubmit;
        this.maxAttempts = builder.maxAttempts;
        this.maxSubmits = builder.maxSubmits;
        this.maxScore = builder.maxScore;
        this.version = builder.version;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * @return the mediaType
     */
    @Nullable
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Return an immutable view of the creators list.
     * @return the creators of this resource
     */
    @Nullable
    public ImmutableList<Agent> getCreators() {
        return creators;
    }

    /**
     * Return an immutable view of the learningObjectives list.
     * @return the learning objectives
     */
    @Nullable
    public ImmutableList<LearningObjective> getLearningObjectives() {
        return learningObjectives;
    }

    /**
     * Return an immutable view of the keywords list.
     * @return the keywords
     */
    @Nullable
    public ImmutableList<String> getKeywords() {
        return keywords;
    }

    /**
     * @return the parent reference.
     */
    @Nullable
    public Entity getIsPartOf() {
        return isPartOf;
    }

    /**
     * @return date published
     */
    @Nullable
    public DateTime getDatePublished() {
        return datePublished;
    }

    /**
     * @return the dateToActivate
     */
    @Nullable
    public DateTime getDateToActivate() {
        return dateToActivate;
    }

    /**
     * @return the dateToShow
     */
    @Nullable
    public DateTime getDateToShow() {
        return dateToShow;
    }

    /**
     * @return the dateToStartOn
     */
    @Nullable
    public DateTime getDateToStartOn() {
        return dateToStartOn;
    }

    /**
     * @return the dateToSubmit
     */
    @Nullable
    public DateTime getDateToSubmit() {
        return dateToSubmit;
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
     * @return the maxSubmits
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int getMaxSubmits() {
        return maxSubmits;
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
     * @return the version
     */
    @Nullable
    public String getVersion() {
        return version;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends BaseEntity.Builder<T>  {
        private String type;
        private String mediaType;
        private List<Agent> creators = Lists.newArrayList();
        private List<LearningObjective> learningObjectives = Lists.newArrayList();
        private List<String> keywords = Lists.newArrayList();
        private Entity isPartOf;
        private DateTime datePublished, dateToActivate, dateToShow, dateToStartOn, dateToSubmit;
        private int maxAttempts, maxSubmits;
        private double maxScore;
        private String version;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.ASSIGNABLE_DIGITAL_RESOURCE.getValue());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param mediaType
         * @return builder.
         */
        public T mediaType(String mediaType) {
            this.mediaType = mediaType;
            return self();
        }

        /**
         * @param creators
         * @return builder.
         */
        public T creators(List<Agent> creators) {
            this.creators = creators;
            return self();
        }

        /**
         * @param creator
         * @return builder.
         */
        public T creator(Agent creator) {
            this.creators.add(creator);
            return self();
        }

        /**
         * @param learningObjectives
         * @return builder.
         */
        public T learningObjectives(List<LearningObjective> learningObjectives) {
            this.learningObjectives = learningObjectives;
            return self();
        }

        /**
         * @param learningObjective
         * @return builder.
         */
        public T learningObjective(LearningObjective learningObjective) {
            this.learningObjectives.add(learningObjective);
            return self();
        }

        /**
         * @param keywords
         * @return builder.
         */
        public T keywords(List<String> keywords) {
            this.keywords = keywords;
            return self();
        }

        /**
         * @param keyword
         * @return builder.
         */
        public T keyword(String keyword) {
            this.keywords.add(keyword);
            return self();
        }

        /**
         * @param isPartOf
         * @return builder.
         */
        public T isPartOf(Entity isPartOf) {
            this.isPartOf = isPartOf;
            return self();
        }

        /**
         * @param datePublished
         * @return builder.
         */
        public T datePublished(DateTime datePublished) {
            this.datePublished = datePublished;
            return self();
        }

        /**
         * @param dateToActivate
         * @return builder
         */
        public T dateToActivate(DateTime dateToActivate) {
            this.dateToActivate = dateToActivate;
            return self();
        }

        /**
         * @param dateToShow
         * @return builder
         */
        public T dateToShow(DateTime dateToShow) {
            this.dateToShow = dateToShow;
            return self();
        }

        /**
         * @param dateToStartOn
         * @return builder
         */
        public T dateToStartOn(DateTime dateToStartOn) {
            this.dateToStartOn = dateToStartOn;
            return self();
        }

        /**
         * @param dateToSubmit
         * @return builder
         */
        public T dateToSubmit(DateTime dateToSubmit) {
            this.dateToSubmit = dateToSubmit;
            return self();
        }

        /**
         * @param maxAttempts
         * @return builder
         */
        public T maxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return self();
        }

        /**
         * @param maxSubmits
         * @return builder
         */
        public T maxSubmits(int maxSubmits) {
            this.maxSubmits = maxSubmits;
            return self();
        }

        /**
         * @param maxScore
         * @return builder
         */
        public T maxScore(double maxScore) {
            this.maxScore = maxScore;
            return self();
        }

        /**
         * @param version
         * @return builder.
         */
        public T version(String version) {
            this.version = version;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Target.
         */
        public AssignableDigitalResource build() {
            return new AssignableDigitalResource(this);
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