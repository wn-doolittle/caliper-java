package org.imsglobal.caliper.entities.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.w3c.Membership;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * A collection of people organized together into a community or other social, commercial or political structure.
 * The group has some common purpose or reason for existence which goes beyond the set of people belonging to it
 * and can act as an Agent. Organizations are often decomposable into hierarchical structures.
 */

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "membership",
    "subOrganizationOf",
    "extensions",
    "dateCreated",
    "dateModified" })
public class Organization extends Entity implements org.imsglobal.caliper.entities.w3c.Organization {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("membership")
    private final ImmutableList<Membership> memberships;

    @JsonProperty("subOrganizationOf")
    private final org.imsglobal.caliper.entities.w3c.Organization subOrganizationOf;

    /**
     * @param builder apply builder object properties to the Organization object.
     */
    protected Organization(Builder<?> builder) {
        super(builder);

        EntityValidator.checkType(builder.type, EntityType.ORGANIZATION);

        this.type = builder.type;
        this.memberships = ImmutableList.copyOf(builder.memberships);
        this.subOrganizationOf = builder.subOrganizationOf;
    }

    /**
     * @return the type
     */
    @Override
    @Nonnull
    public EntityType getType() {
        return type;
    }

    /**
     * @return membership
     */
    @Nullable
    public ImmutableList<Membership> getMembership() {
        return memberships;
    }

    /**
     * @return parent organization
     */
    @Nullable
    public org.imsglobal.caliper.entities.w3c.Organization getSubOrganizationOf() {
        return subOrganizationOf;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private EntityType type;
        private List<Membership> memberships = Lists.newArrayList();
        private org.imsglobal.caliper.entities.w3c.Organization subOrganizationOf;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(EntityType.ORGANIZATION);
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(EntityType type) {
            this.type = type;
            return self();
        }

        /**
         * @param memberships
         * @return builder.
         */
        public T memberships(List<Membership> memberships) {
            this.memberships = memberships;
            return self();
        }

        /**
         * @param membership
         * @return builder.
         */
        public T membership(Membership membership) {
            this.memberships.add(membership);
            return self();
        }

        /**
         * @param subOrganizationOf
         * @return builder.
         */
        public T subOrganizationOf(org.imsglobal.caliper.entities.w3c.Organization subOrganizationOf) {
            this.subOrganizationOf = subOrganizationOf;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the Organization.
         */
        public Organization build() {
            return new Organization(this);
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