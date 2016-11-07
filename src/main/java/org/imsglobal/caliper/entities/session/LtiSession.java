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

package org.imsglobal.caliper.entities.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.EntityType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LtiSession extends Session {
    @JsonProperty("@type")
    private final String type;

    @JsonProperty("launchParameters")
    private final Object launchParameters;

    /**
     * @param builder apply builder object properties to the LtiSession object.
     */
    protected LtiSession(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
        this.launchParameters = builder.launchParameters;
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
     * @return the LTI launchParameters
     */
    @Nullable
    public Object getLaunchParameters() {
        return launchParameters;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Session.Builder<T>  {
        private String type;
        private Object launchParameters;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.LTI_SESSION.getValue());
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
         * @param launchParameters
         * @return builder.
         */
        public T launchParameters(Object launchParameters) {
            this.launchParameters = launchParameters;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Session.
         */
        public LtiSession build() {
            return new LtiSession(this);
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