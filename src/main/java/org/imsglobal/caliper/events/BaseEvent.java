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

package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.context.Context;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.validators.EventValidator;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;

/**
 * This class provides a skeletal implementation of the Event interface in order to minimize the effort
 * required to implement the interface and achieve Level 0 conformance with the Caliper specification.
 * To implement a new Event type, perhaps with additional properties and/or behaviors specified, a developer
 * need only extend this class with a concrete implementation.
 *
 * Note that the Event interface specifies the minimum set of properties required to implement a Caliper Event.
 * Inclusion of the learning context within which learning activities occur is not required.  However, events that
 * are generated without reference to context will generally fail to reflect the Event model defined by most Metric
 * Profiles as well as Level 1+ conformance requirements.
 */
public abstract class BaseEvent implements Event {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("id")
    private final String id;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("action")
    protected final String action;

    @JsonProperty("object")
    private final Entity object;

    @JsonProperty("eventTime")
    private final DateTime eventTime;

    @JsonIgnore
    // private static final Logger log = LoggerFactory.getLogger(BaseEventContext.class);

    /**
     * Utilize builder to construct Event.  Validate object copy rather than the
     * builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected BaseEvent(Builder<?> builder) {

        EventValidator.checkContext(builder.context, Context.CONTEXT);

        this.context = builder.context;
        this.id = builder.id;
        this.type = builder.type;
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
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
     * Identifier that must be set either by emitting service or the receiving endpoint.
     * @return the identifier
     */
    @Nonnull
    public String getId() {
        return id;
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
        private String id;
        private String type;
        private Agent actor;
        private String action;
        private Entity object;
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
         * @param id
         * @return builder.
         */
        public T id(String id) {
            this.id = id;
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
         * @param eventTime
         * @return builder.
         */
        public T eventTime(DateTime eventTime) {
            this.eventTime = eventTime;
            return self();
        }
    }

    /**
     * Self-reference that permits sub-classing of builder.
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }
}