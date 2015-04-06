package org.imsglobal.caliper.entities.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.w3c.Membership;
import org.imsglobal.caliper.validators.EntityValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "description",
    "hasMembership",
    "extensions",
    "dateCreated",
    "dateModified" })
public class Person extends Entity implements Agent {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("hasMembership")
    private ImmutableList<Membership> hasMemberships;

    /**
     * @param builder apply builder object properties to the Person object.
     */
    protected Person(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, Type.PERSON);

        this.type = builder.type;
        this.hasMemberships = ImmutableList.copyOf(builder.hasMemberships);
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
     * @return memberships
     */
    @Nullable
    public ImmutableList<Membership> hasMembership() {
        return hasMemberships;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;
        private List<Membership> hasMemberships = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Entity.Type.PERSON.uri());
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
         * @param memberships
         * @return builder.
         */
        public T hasMemberships(List<Membership> memberships) {
            this.hasMemberships = memberships;
            return self();
        }

        /**
         * @param membership
         * @return builder.
         */
        public T hasMembership(Membership membership) {
            this.hasMemberships.add(membership);
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the Person.
         */
        public Person build() {
            return new Person(this);
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