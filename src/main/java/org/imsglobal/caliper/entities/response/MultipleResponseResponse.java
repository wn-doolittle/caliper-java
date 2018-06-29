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

package org.imsglobal.caliper.entities.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.TimePeriod;
import org.imsglobal.caliper.entities.resource.Attempt;
import org.imsglobal.caliper.validators.EntityValidator;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Representation of a response to a multiple choice question that permits one or more
 * options to be selected.
 */
public class MultipleResponseResponse extends AbstractEntity implements CaliperResponse {

    @JsonProperty("attempt")
    private Attempt attempt;

    @JsonProperty("values")
    private ImmutableList<String> values;

    @JsonIgnore
    private TimePeriod timePeriod = new TimePeriod();

    /**
     * @param builder apply builder object properties to the Response object.
     */
    protected MultipleResponseResponse(Builder<?> builder) {
        super(builder);

        EntityValidator.checkStartTime(builder.timePeriod.getStartedAtTime(), builder.timePeriod.getEndedAtTime());
        EntityValidator.checkDuration(builder.timePeriod.getDuration());

        this.attempt = builder.attempt;
        this.values = ImmutableList.copyOf(builder.values);
        this.timePeriod.setStartedAtTime(builder.timePeriod.getStartedAtTime());
        this.timePeriod.setEndedAtTime(builder.timePeriod.getEndedAtTime());
        this.timePeriod.setDuration(builder.timePeriod.getDuration());
    }

    /**
     * @return attempt associated with the response;
     */
    @Nonnull
    public Attempt getAttempt() {
        return attempt;
    }

    /**
     * @return response values
     */
    @Nullable
    public List<String> getValues() {
        return values;
    }

    /**
     * @return started at time
     */
    @Nullable
    public DateTime getStartedAtTime() {
        return timePeriod.getStartedAtTime();
    }

    /**
     * @return ended at time
     */
    @Nullable
    public DateTime getEndedAtTime() {
        return timePeriod.getEndedAtTime();
    }

    /**
     * @return duration
     */
    @Nullable
    public String getDuration() {
        return timePeriod.getDuration();
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T>  {
        private Attempt attempt;
        private List<String> values = Lists.newArrayList();
        private TimePeriod timePeriod = new TimePeriod();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            super.type(EntityType.MULTIPLERESPONSE);
        }

        /**
         * @param attempt
         * @return builder.
         */
        public T attempt(Attempt attempt) {
            this.attempt = attempt;
            return self();
        }

        /**
         * @param values
         * @return builder.
         */
        public T values(List<String> values) {
            if(values != null) {
                this.values.addAll(values);
            }
            return self();
        }

        /**
         * @param value
         * @return builder.
         */
        public T value(String value) {
            this.values.add(value);
            return self();
        }

        /**
         * @param startedAtTime
         * @return
         */
        public T startedAtTime(DateTime startedAtTime) {
            this.timePeriod.setStartedAtTime(startedAtTime);

            return self();
        }

        /**
         * @param endedAtTime
         * @return builder
         */
        public T endedAtTime(DateTime endedAtTime) {
            this.timePeriod.setEndedAtTime(endedAtTime);
            return self();
        }

        /**
         * @param duration
         * @return
         */
        public T duration(String duration) {
            this.timePeriod.setDuration(duration);
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of MultipleResponseResponse.
         */
        public MultipleResponseResponse build() {
            return new MultipleResponseResponse(this);
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