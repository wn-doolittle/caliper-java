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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
 * This class provides a skeletal implementation of the Event interface together with a representation of the 
 * learning context in order to minimize the effort required to implement a contextualized Caliper Event. 
 * To implement a new Event type with properties that provide a means to represent the context within 
 * which the event occurred as required by most Metric Profiles, a developer need only extend this class with 
 * a concrete implementation. 
 */
public abstract class BaseEventContext implements Event {

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

    @JsonProperty("target")
    private final Targetable target;

    @JsonProperty("generated")
    private final Generatable generated;

    @JsonProperty("eventTime")
    private final DateTime eventTime;

    @JsonProperty("edApp")
    private final SoftwareApplication edApp;

    @JsonProperty("group")
    private final Organization group;

    @JsonProperty("membership")
    private final Membership membership;

    @JsonProperty("federatedSession")
    private final Session federatedSession;

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
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
        this.generated = builder.generated;
        this.eventTime = builder.eventTime;
        this.edApp = builder.edApp;
        this.group = builder.group;
        this.membership = builder.membership;
        this.federatedSession = builder.federatedSession;
    }

    /**
     * The JSON-LD context provides a mapping of terms to IRIs.  The identifier
     * should be expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the context IRI.
     */
    @Nonnull
    public String getContext() {
        return context;
    }

    /**
     * Specifies the type of event or node in the graph as defined by JSON-LD.  The type should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the event type IRI
     */
    @Nonnull
    public String getType() {
        return type;
    }

    /**
     * The actor engaged in the interaction.  Analogous to a subject.
     * @return the actor
     */
    @Nonnull
    public Agent getActor() {
        return actor;
    }

    /**
     * The action undertaken by the actor.  Analogous to a predicate or verb.  The action should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the action undertaken by the actor
     */
    @Nonnull
    public String getAction() {
        return action;
    }

    /**
     * The object of the interaction.  The object should be expressed as a unique IRI in conformance
     * with the JSON-LD specification.
     * @return the object of the interaction
     */
    @Nonnull
    public Entity getObject() {
        return object;
    }

    /**
     * An Entity that represents the target of a learning interaction.  Navigating to a resource while engaged in a
     * learning activity is an example of a target entity.
     * @return target entity
     */
    @Nullable
    public Targetable getTarget() {
        return target;
    }

    /**
     * An Entity generated as a result of the learning interaction.  Example entities typed as Generatable include
     * assignable attempts, assessment item responses and graded outcome results.
     * @return generated entity
     */
    @Nullable
    public Generatable getGenerated() {
        return generated;
    }

    /**
     * A combined date and time representation (including milliseconds) of when an event occurred,
     * formatted in accordance with the ISO 8601 standard.
     * @return the event time
     */
    @Nonnull
    public DateTime getEventTime() {
        return eventTime;
    }

    /**
     * The module, application, platform, system and/or service that provides the technological context within which
     * the learning activity occurs.
     * @return the edApp context
     */
    @Nullable
    public SoftwareApplication getEdApp() {
        return edApp;
    }

    /**
     * The Group represents a collection of people organized together into a community or other social, commercial
     * or political structure.  The group has some common purpose or reason for existence which goes beyond the set
     * of people belonging to it and can act as an Agent.  For learning interactions, a typical group context would
     * comprise the course context within which the learning activity occurs.
     * @return the group context
     */
    @Nullable
    public Organization getGroup() {
        return group;
    }

    /**
     * The Membership context defines an actor's roles and status as a member of an organization or group.
     * @return the membership context.
     */
    @Nullable
    public Membership getMembership() {
        return membership;
    }

    /**
     * An LTI-scoped identifier representing the originating Tool Consumer user session during which one or more launch
     * requests to external Tool Providers are initiated.  Provision of a federated session identifier helps to
     * correlate Tool Provider learning activities with those of the Tool Consumer during the same "logical" session.
     * @return the federated session
     */
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "@id")
    @JsonIdentityReference(alwaysAsId = true)
    @Nullable
    public Session getFederatedSession() {
        return federatedSession;
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
        private Targetable target;
        private Generatable generated;
        private DateTime eventTime;
        private SoftwareApplication edApp;
        private Organization group;
        private Membership membership;
        private Session federatedSession;

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
         * @param federatedSession
         * @return builder.
         */
        public T federatedSession(Session federatedSession) {
            this.federatedSession = federatedSession;
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