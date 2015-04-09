package org.imsglobal.caliper.entities.lis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * A Caliper LIS Group represents a Course substructure that a Person is able to join as a member.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "credits",
    "category",
    "academicSession",
    "membership",
    "subOrganizationOf",
    "extensions",
    "dateCreated",
    "dateModified" })
public class Group extends Entity implements org.imsglobal.caliper.entities.w3c.Organization {

    @JsonProperty("@type")
    private final EntityType type;

    @JsonProperty("membership")
    private final ImmutableList<org.imsglobal.caliper.entities.w3c.Membership> memberships;

    @JsonProperty("subOrganizationOf")
    private final Course subOrganizationOf;

    /**
     * @param builder apply builder object properties to the Group object.
     */
    protected Group(Builder<?> builder) {
        super(builder);

        EntityValidator.checkType(builder.type, EntityType.GROUP);

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
    public ImmutableList<org.imsglobal.caliper.entities.w3c.Membership> getMembership() {
        return memberships;
    }

    /**
     * @return the parent membership
     */
    @Nonnull
    public Course getSubOrganizationOf() {
        return subOrganizationOf;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private EntityType type;
        private List<org.imsglobal.caliper.entities.w3c.Membership> memberships = Lists.newArrayList();
        private Course subOrganizationOf;

        /**
         * Initialize type with default value.  Required if the group type is not specified by the user.
         */
        public Builder() {
            type(EntityType.GROUP);
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
        public T memberships(List<org.imsglobal.caliper.entities.w3c.Membership> memberships) {
            this.memberships = memberships;
            return self();
        }

        /**
         * @param membership
         * @return builder.
         */
        public T membership(org.imsglobal.caliper.entities.w3c.Membership membership) {
            this.memberships.add(membership);
            return self();
        }

        /**
         * @param subOrganizationOf
         * @return builder.
         */
        public T subOrganizationOf(Course subOrganizationOf) {
            this.subOrganizationOf = subOrganizationOf;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of Group.
         */
        public Group build() {
            return new Group(this);
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