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

package org.imsglobal.caliper.entities.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.CaliperReferrer;

import javax.annotation.Nullable;

public class SoftwareApplication extends Agent implements CaliperReferrer {

    @JsonProperty("version")
    private final String version;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected SoftwareApplication(Builder<?> builder) {
        super(builder);
        this.version = builder.version;
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
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends Agent.Builder<T> {
        private String version;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.SOFTWARE_APPLICATION);
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
         * @return a new instance of the SoftwareApplication.
         */
        public SoftwareApplication build() {
            return new SoftwareApplication(this);
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