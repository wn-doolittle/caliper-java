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

package org.imsglobal.caliper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import org.imsglobal.caliper.context.Context;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.events.EventType;
import org.imsglobal.caliper.validators.EventValidator;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class TestFreeFormEvent implements Event {
    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("action")
    protected final String action;

    @JsonProperty("object")
    private final Entity object;

    @JsonProperty("extensions")
    private Map<String, Object> extensions;

    @JsonProperty("eventTime")
    private final DateTime eventTime;

    @JsonIgnore
    //private static final Logger log = LoggerFactory.getLogger(TestMinimalEvent.class);

    /**
     * Utilize builder to construct Event.  Validate object copy rather than the
     * builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected TestFreeFormEvent(Builder<?> builder) {

        EventValidator.checkContext(builder.context, Context.CONTEXT);

        this.context = builder.context;
        this.type = builder.type;
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
        this.extensions = builder.extensions;
        this.eventTime = builder.eventTime;
    }

    /**
     * Required.
     * @return the context
     */
    @Nonnull
    public String getContext() {
        return context;
    }

    /**
     * Required.
     * @return the type
     */
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * Required.
     * @return the actor
     */
    @Nonnull
    public Agent getActor() {
        return actor;
    }

    /**
     * Required.
     * @return the action
     */
    @Nonnull
    public String getAction() {
        return action;
    }

    /**
     * Required.
     * @return the object
     */
    @Nonnull
    public Entity getObject() {
        return object;
    }

    /**
     * Returns a map of additional custom attributes.
     * @return custom extensions (key/value pairs).
     */
    @Nullable
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    /**
     * Required.
     * @return the startedAt time
     */
    @Nonnull
    public DateTime getEventTime() {
        return eventTime;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String context;
        private String type;
        private Agent actor;
        private String action;
        private Entity object;
        private Map<String, Object> extensions = Maps.newHashMap();
        private DateTime eventTime;

        protected abstract T self();

        /**
         * Initialize type with default values.
         */
        public Builder() {
            context(Context.CONTEXT.getValue());
            type(EventType.EVENT.getValue());
        }

        /**
         * @param context
         * @return builder.
         */
        private T context(String context) {
            this.context = context;
            return self();
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
         * @param actor
         * @return builder.
         */
        public T actor(Agent actor) {
            this.actor = actor;
            return self();
        }

        /**
         * @param action
         * @return builder.
         */
        public T action(String action) {
            this.action = action;
            return self();
        }

        /**
         * @param object
         * @return builder.
         */
        public T object(Entity object) {
            this.object = object;
            return self();
        }

        /**
         * @param key
         * @param value
         * @return builder
         */
        public T extension(String key, Object value) {
            this.extensions.put(key, value);
            return self();
        }

        /**
         * @param extensions
         * @return builder
         */
        public T extensions(Map<String, Object> extensions) {
            this.extensions.putAll(extensions);
            return self();
        }

        /**
         * @param eventTime
         * @return builder.
         */
        public T eventTime(DateTime eventTime) {
            this.eventTime = eventTime;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new instance of the AssessmentProfile.
         */
        public TestFreeFormEvent build() {
            return new TestFreeFormEvent(this);
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