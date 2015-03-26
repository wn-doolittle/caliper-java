package org.imsglobal.caliper.entities.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.imsglobal.caliper.entities.Entity;
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
public class SoftwareApplication extends Entity implements org.imsglobal.caliper.entities.foaf.Agent,
                                                           org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication {

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("hasMembership")
    private ImmutableList<Membership> memberships;

    /**
     * @param builder apply builder object properties to the SoftwareApplication object.
     */
    protected SoftwareApplication(Builder<?> builder) {
        super(builder);

        EntityValidator.checkTypeUri(builder.type, Type.SOFTWARE_APPLICATION);

        this.type = builder.type;
        this.memberships = ImmutableList.copyOf(builder.memberships);
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
        return memberships;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends Entity.Builder<T>  {
        private String type;
        private List<Membership> memberships = Lists.newArrayList();

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(Entity.Type.SOFTWARE_APPLICATION.uri());
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
        private T memberships(List<Membership> memberships) {
            this.memberships = memberships;
            return self();
        }

        /**
         * @param membership
         * @return builder.
         */
        private T membership(Membership membership) {
            this.memberships.add(membership);
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