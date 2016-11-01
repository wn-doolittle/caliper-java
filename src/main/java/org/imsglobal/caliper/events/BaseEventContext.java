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
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication;
import org.imsglobal.caliper.entities.session.Session;
import org.imsglobal.caliper.entities.w3c.Membership;
import org.imsglobal.caliper.entities.w3c.Organization;
import org.imsglobal.caliper.validators.EventValidator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class provides a skeletal implementation of both the Event and EventContext interfaces in order to minimize
 * the effort required to implement the interface.  To implement a new Event type with properties that provide a
 * means to represent the learning context within which the event occurred required by most Metric Profiles,
 * a developer need only extend this class with a concrete implementation.
 */
public abstract class BaseEventContext implements Event, EventContext {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("id")
    private final String id;

    @JsonProperty("actor")
    private final Agent actor;

    @JsonProperty("action")
    protected final String action;

    @JsonProperty("object")
    private final Entity object;

    @JsonProperty("target")
    private final Targetable target;

    @JsonProperty("generated")
    private final Generatable generated;

    @JsonProperty("referrer")
    private final Entity referrer;

    @JsonProperty("eventTime")
    private final DateTime eventTime;

    @JsonProperty("edApp")
    private final SoftwareApplication edApp;

    @JsonProperty("group")
    private final Organization group;

    @JsonProperty("membership")
    private final Membership membership;

    @JsonProperty("session")
    private final Session session;

    @JsonProperty("federatedSession")
    private final Session federatedSession;

    @JsonProperty("extensions")
    private final Object extensions;

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(BaseEventContext.class);

    /**
     * Utilize builder to construct Event.  Validate object copy rather than the
     * builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected BaseEventContext(Builder<?> builder) {

        EventValidator.checkContext(builder.context, Context.CONTEXT);

        this.context = builder.context;
        this.type = builder.type;
        this.id = builder.id;
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
        this.generated = builder.generated;
        this.referrer = builder.referrer;
        this.eventTime = builder.eventTime;
        this.edApp = builder.edApp;
        this.group = builder.group;
        this.membership = builder.membership;
        this.session = builder.session;
        this.federatedSession = builder.federatedSession;
        this.extensions = builder.extensions;
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
     * Optional
     * @return the id
     */
    @Nullable
    public String getId() {
        return id;
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
     * Optional.
     * @return the target
     */
    @Nullable
    public Targetable getTarget() {
        return target;
    }

    /**
     * Optional.
     * @return generated
     */
    @Nullable
    public Generatable getGenerated() {
        return generated;
    }

    /**
     * Optional.
     * @return referrer
     */
    @Nullable
    public Entity getReferrer() {
        return referrer;
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
     * The edApp context, part of the Caliper Learning Context.  Optional.
     * @return the edApp
     */
    @Nullable
    public SoftwareApplication getEdApp() {
        return edApp;
    }

    /**
     * The Group context, part of the Caliper Learning Context.  Optional.
     * @return the group
     */
    @Nullable
    public Organization getGroup() {
        return group;
    }

    /**
     * The Membership context, part of the Caliper Learning Context.  Optional.
     * @return the membership
     */
    @Nullable
    public Membership getMembership() {
        return membership;
    }

    /**
     * Current session context.  Optional.
     * @return the federated session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Federated Session object, part of the LTI launch context.  Optional.
     * @return the federated session
     */
    public Session getFederatedSession() {
        return federatedSession;
    }

    /**
     * Custom properties.  Optional.
     * @return extensions
     */
    public Object getExtensions() {
        return extensions;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String context;
        private String type;
        private String id;
        private Agent actor;
        private String action;
        private Entity object;
        private Targetable target;
        private Generatable generated;
        private Entity referrer;
        private DateTime eventTime;
        private SoftwareApplication edApp;
        private Organization group;
        private Membership membership;
        private Session session;
        private Session federatedSession;
        private Object extensions;

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
         * @param id
         * @return builder.
         */
        private T id(String id) {
            this.id = id;
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
         * @param target
         * @return builder.
         */
        public T target(Targetable target) {
            this.target = target;
            return self();
        }

        /**
         * @param generated
         * @return builder.
         */
        public T generated(Generatable generated) {
            this.generated = generated;
            return self();
        }

        /**
         * @param referrer
         * @return builder.
         */
        public T referrer(Entity referrer) {
            this.referrer = referrer;
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
         * @param edApp
         * @return builder.
         */
        public T edApp(SoftwareApplication edApp) {
            this.edApp = edApp;
            return self();
        }

        /**
         * @param group
         * @return builder.
         */
        public T group(Organization group) {
            this.group = group;
            return self();
        }

        /**
         * @param membership
         * @return builder.
         */
        public T membership(Membership membership) {
            this.membership = membership;
            return self();
        }

        /**
         * @param session
         * @return builder.
         */
        public T session(Session session) {
            this.session = session;
            return self();
        }

        /**
         * @param federatedSession
         * @return builder.
         */
        public T federatedSession(Session federatedSession) {
            this.federatedSession = federatedSession;
            return self();
        }

        /**
         * @param extensions
         * @return builder.
         */
        public T extensions(Object extensions) {
            this.extensions = extensions;
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